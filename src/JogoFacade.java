package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class JogoFacade {
    private static final Scanner Teclado = Entrada.getScanner();
    private static final Tela tela = new Tela();

    public JogoFacade(){

    }

    public int getNumJogadores() {
        tela.pedirNumJogadores();
        int numJogadores = Teclado.nextInt();
        Teclado.nextLine(); // Limpar o buffer do scanner
        if(numJogadores < 2 || numJogadores > 6) {
            tela.mostrarErro("número de jogadores inválido! Deve ser entre 2 e 6.");
            return getNumJogadores(); // Chama recursivamente até obter um valor válido
        }
        return numJogadores;
    }   

    public int getNumCasas() {
        tela.pedirNumCasas();
        int numCasas = Teclado.nextInt();
        Teclado.nextLine(); // Limpar o buffer do scanner
        if(numCasas < 10 || numCasas > 100) {
            tela.mostrarErro("Número de casas inválido! Deve ser entre 10 e 100.");
            return getNumCasas(); // Chama recursivamente até obter um valor válido
        }
        return numCasas;
    }

    public void config(int numJogadores){
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        tabuleiro.getJogadores().clear(); // Limpa a lista de jogadores antes de iniciar
        Set<Integer> tipos  = new HashSet<>();
        int certo = -1;
        while (certo == -1){
            int[] cor = new int[numJogadores];
            int esc = 0;
            int [] recebeTipo = criandoJogadoresValidos(numJogadores, cor, esc);
            for (int h=0; h < numJogadores; h++){
                tipos.add(recebeTipo[h]);
            }

            if(tipos.size() < 2){
                    tela.mostrarErro("é necessário ter pelo menos dois tipos diferentes de jogador!");
                    tabuleiro.getJogadores().clear();
            } else {
                certo = 0;
                tela.mostrarIniciandoJogo();
            }
            
        }
    }

    public static  int[] criandoJogadoresValidos(int numJogadores, int[] cor, int esc) {
        int[] jogadorTipo = new int[numJogadores];
        for (int i = 0; i < numJogadores; i++) {
            cor[i] = escolherCorJogador(i + 1, cor, i);
            jogadorTipo[i] = escolherTipoJogadorPorCor(cor[i]);
        }

        // Verifica se há pelo menos dois tipos diferentes
        Set<Integer> tipos = new HashSet<>();
        for (int tipo : jogadorTipo) {
            tipos.add(tipo);
        }
        if (tipos.size() < 2) {
            tela.mostrarErro("É necessário ter pelo menos dois tipos diferentes de jogador!");
            Tabuleiro.getInstancia().getJogadores().clear();
            return new int[0];
        }

        return jogadorTipo;
    }

    private static int escolherCorJogador(int jogadorNum, int[] cor, int atual) {
        while (true) {
            tela.pedirCorJogador(jogadorNum);
            int esc = Teclado.nextInt();
            if (esc < 1 || esc > 6) {
                tela.mostrarErro("Cor inválida! Tente novamente! Escolha entre 1 e 6.");
                continue;
            }
            boolean corRepetida = false;
            for (int j = 0; j < atual; j++) {
                if (cor[j] == esc) {
                    corRepetida = true;
                    break;
                }
            }
            if (corRepetida) {
                tela.mostrarErro("Essa cor já foi escolhida. Tente outra.");
            } else {
                return esc;
            }
        }
    }

    private static int escolherTipoJogadorPorCor(int cor) {
        String corNome = corParaNome(cor);
        return escolherTipoJogador(corNome);
    }

    private static String corParaNome(int cor) {
        switch (cor) {
            case 1: return "Azul";
            case 2: return "Verde";
            case 3: return "Amarelo";
            case 4: return "Laranja";
            case 5: return "Vermelho";
            case 6: return "Rosa";
            default: return "Desconhecida";
        }
    }

    private static int escolherTipoJogador(String cor) {
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        int tipo;
        while (true) {
            tela.pedirTipoJogador(cor);
            tipo = Teclado.nextInt();
            if (tipo < 1 || tipo > 3) {
                tela.mostrarErro("tipo inválido, tente novamente");
                continue;
            }
            tabuleiro.adicionarJogadores(JogadorFactory.criarJogador(cor, 0, tipo));
            return tipo;
        }
    }

    public void configTabuleiro(int numCasas) {
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        FactoryCasa factoryCasa = new FactoryCasa();
        
        tela.pedirNumCasasEspeciais();
        int numCasasEspeciais = Teclado.nextInt();
        ArrayList<Integer> casasEspeciais = new ArrayList<>();
        for(int i = 0; i < numCasasEspeciais; i++){
            tela.pedirCasaEspecial(i + 1);
            int casa = Teclado.nextInt();
            casasEspeciais.add(casa);
        }
        
        tabuleiro.adicionarCasa(factoryCasa.fazerCasa(0)); //casas 0 criada
        tabuleiro.getCasas().clear();
        for (int i = 0; i < numCasas; i++) {
            if (casasEspeciais.contains(i)) {
                tela.pedirTipoCasa(i);
                int escolha = Teclado.nextInt();

                if (escolha < 1 || escolha > 7) {
                    tela.mostrarErro("Tipo de casa inválido! Tente novamente.");
                    i--;
                    continue;
                }

                tabuleiro.adicionarCasa(factoryCasa.fazerCasa(escolha));
            } else {
                tabuleiro.adicionarCasa(factoryCasa.fazerCasa(0));
            }
        }
    }

    public void printTabuleiro() {
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        tela.saidaGeral("\n📍 Estado atual do tabuleiro:");
        for (int i = 0; i < tabuleiro.getCasas().size(); i++) {
            String jogadoresNaCasa = "";
            for (int j = 0; j < tabuleiro.getJogadores().size(); j++) {
                if (tabuleiro.getCasaJogador(j) == i) {
                    jogadoresNaCasa += "[" + tabuleiro.getJogadores().get(j).getCor().charAt(0) + "]";
                }
            }
            System.out.println("Casa " + i + ": " + jogadoresNaCasa);
        }
    }


    public void startJogo(){
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        tabuleiro.criarCasaJogador();
        Modo modoJogo = new Modo();
        int modo = modoJogo.getModo();
        int rodada = 0;
        int jogadorVitorioso = -1;
        while(jogadorVitorioso == -1){
            rodada++;
            if (modo == 1) {
            	jogadorVitorioso = modoJogo.Normal(rodada, tabuleiro); 
            }
            else if (modo ==2) {
            	jogadorVitorioso = modoJogo.Debug(rodada, tabuleiro);
            }
            else {
            	tela.escolhaInvalidaModo();
            }    
        }
        tela.finalJogo(jogadorVitorioso, rodada, tabuleiro);
    }
}