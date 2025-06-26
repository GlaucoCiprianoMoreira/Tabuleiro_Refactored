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
                    tela.mostrarErro("essa cor já foi escolhida. Tente outra.");
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
            if(tipo < 1 || tipo > 3){
                tela.mostrarErro("tipo inválido, tente novamente");
                continue; // Pula para a próxima iteração do loop
            }
            else{
                flag = false; // Tipo válido, sai do loop
            }
            tabuleiro.adicionarJogadores(JogadorFactory.criarJogador(cor, indice, tipo));
            
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
            if(escolha < 1 || escolha > 7) {
                tela.mostrarErro("Tipo de casa inválido! Tente novamente.");
                i--;
                continue; // Pula para a próxima iteração do loop
            }
            tabuleiro.adicionarCasa(factoryCasa.fazerCasa(escolha));
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
            	tela.saidaGeral("Escolha inválida!");
            }    
        }
        tela.finalJogo(jogadorVitorioso, rodada, tabuleiro);
    }
}