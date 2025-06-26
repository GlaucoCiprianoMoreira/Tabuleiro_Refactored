package src;

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
            /*while(numJogadores < 2 || numJogadores > 6){
                System.out.print("Digite o número de jogadores: ");
                numJogadores = Teclado.nextInt();
                if(numJogadores < 2){
                    System.out.println("Número mínimo de jogadores é 2.");
                }else if(numJogadores > 6){
                    System.out.println("Número máximo de jogadores é 6.");
                }
            }*/
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

    public static  int[] criandoJogadoresValidos(int numJogadores, int[] cor, int esc){
        int [] jogadorTipo = new int [numJogadores];
        for(int i = 0; i < numJogadores; i++){
            
            boolean corValida = false;

            while(!corValida){
                tela.pedirCorJogador(i+1);
                esc = Teclado.nextInt();

                boolean corRepetida = false;
                for(int j = 0; j < i; j++){
                    if(cor[j] == esc){
                        corRepetida = true;
                        break;
                    }
                }

                if(corRepetida){
                    tela.mostrarErro("essa cor já foi escolhida. Ten  te outra.");
                } else {
                    cor[i] = esc;
                    corValida = true;
                }
            }
            switch(esc){
                case 1:
                    jogadorTipo[i] = escolherJogador("Azul");
                    break;
                case 2:
                    jogadorTipo[i] = escolherJogador("Verde");
                    break;
                case 3:
                    jogadorTipo[i] = escolherJogador("Amarelo");
                    break;
                case 4:
                    jogadorTipo[i] = escolherJogador("Laranja");
                    break;
                case 5:
                    jogadorTipo[i] = escolherJogador("Vermelho");
                    break;
                case 6:
                    jogadorTipo[i] = escolherJogador("Rosa");
                    break;                      
            }
        }

        return jogadorTipo;
    }

    public static int escolherJogador(String cor){
        boolean flag = true;
        int indice = 0;
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        int tipo = 0;
        while(flag == true){ // TEM QUE TER PELO MENOS DOIS TIPOS DIFERENTES = TODOS NÃO PODEM SER IGUAIS
            tela.pedirTipoJogador(cor);
            tipo = Teclado.nextInt();
            switch(tipo){
                case 1:
                    tabuleiro.adicionarJogadores(new Sortudo(cor, indice));
                    flag = false;
                    tipo = 1;
                    break;
                case 2:
                    tabuleiro.adicionarJogadores(new Normal(cor, indice));
                    flag = false;
                    tipo = 2;
                    break;
                case 3:
                    tabuleiro.adicionarJogadores(new Azarado(cor, indice));
                    flag = false;
                    tipo = 3;
                    break;
                default:
                    tela.mostrarErro("tipo inválido, tente novamente");
            }
            indice++; 
        }
        return tipo;
    }

    public void configTabuleiro(int numCasas) {
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        FactoryCasa factoryCasa = new FactoryCasa();
        tabuleiro.getCasas().clear(); // Limpa a lista de casas antes de iniciar
        for(int i =0; i < numCasas; i++){
            tela.pedirTipoCasa(i+1);
            int escolha = Teclado.nextInt();
            tabuleiro.adicionarCasa(factoryCasa.fazerCasa(escolha));
        }
    }

    public void printTabuleiro() {
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        System.out.println("\n📍 Estado atual do tabuleiro:");
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
            	System.out.println("Escolha inválida!");
            }    
        }
        System.out.println("\nRodada " + rodada + " - Fim de jogo!");
        System.out.println("Jogador " + tabuleiro.getJogadores().get(jogadorVitorioso).getCor() + " vitorioso.");
        System.out.println("Número de jogadas dos jogadores:");
        for (int j=0; j<tabuleiro.getJogadores().size(); j++) {
        	System.out.println("Número de jogadas do jogador " + tabuleiro.getJogadores().get(j).getCor() + " é " + tabuleiro.getJogadores().get(j).getNumJogadas());
        }
    }
}