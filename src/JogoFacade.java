package src;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class JogoFacade {
    private static Tabuleiro tabuleiro; 
    private static final Scanner Teclado = Entrada.getScanner();
    
    public static int getNumJogadores() {
        System.out.print("Digite o número de jogadores (entre 2 e 6): ");
        int numJogadores = Teclado.nextInt();
        Teclado.nextLine(); // Limpar o buffer do scanner
        if(numJogadores < 2 || numJogadores > 6) {
            System.out.println("Número de jogadores inválido! Deve ser entre 2 e 6.");
            return getNumJogadores(); // Chama recursivamente até obter um valor válido
        }
        return numJogadores;
    }   

    public static int getNumCasas() {
        System.out.print("Digite o número de casas do tabuleiro: ");
        int numCasas = Teclado.nextInt();
        Teclado.nextLine(); // Limpar o buffer do scanner
        if(numCasas < 9 || numCasas > 100) {
            System.out.println("Número de casas inválido! Deve ser entre 10 e 100.");
            return getNumCasas(); // Chama recursivamente até obter um valor válido
        }
        return numCasas;
    }

    public void config(int numJogadores){
        Set<Integer> tipos  = new HashSet<>();
        int certo = -1;
        while (certo ==-1){
            while(numJogadores < 2 || numJogadores > 6){
                System.out.print("Digite o número de jogadores: ");
                numJogadores = Teclado.nextInt();
                if(numJogadores < 2){
                    System.out.println("Número mínimo de jogadores é 2.");
                }else if(numJogadores > 6){
                    System.out.println("Número máximo de jogadores é 6.");
                }
            }
            int[] cor = new int[numJogadores];
            int esc = 0;

            int [] recebeTipo = criandoJogadoresValidos(numJogadores, cor, esc);
            for (int h=0; h < numJogadores; h++){
                tipos.add(recebeTipo[h]);
            }
            if(tipos.size() < 2){
                    System.out.println("É necessário ter pelo menos dois tipos diferentes de jogador!");
                    tabuleiro.getJogadores().clear();
                    System.out.println("Recomençando o jogo!");
            } else {
                certo = 0;
            }
            System.out.println("Jogadores criados com sucesso!");
            System.out.println("Iniciando o jogo...");
            

        }
    }

    public static  int[] criandoJogadoresValidos(int numJogadores, int[] cor, int esc){
        int [] jogadorTipo = new int [numJogadores];
        for(int i = 0; i < numJogadores; i++){
            
            boolean corValida = false;

            while(!corValida){
                System.out.println("Jogador " + (i + 1) + ", escolha a cor:");
                System.out.println("1 - Azul");
                System.out.println("2 - Verde");    
                System.out.println("3 - Amarelo");
                System.out.println("4 - Laranja");
                System.out.println("5 - Vermelho");
                System.out.println("6 - Rosa");
                System.out.print("-> ");
                esc = Teclado.nextInt();

                boolean corRepetida = false;
                for(int j = 0; j < i; j++){
                    if(cor[j] == esc){
                        corRepetida = true;
                        break;
                    }
                }

                if(corRepetida){
                    System.out.println("Essa cor já foi escolhida. Ten  te outra.");
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
            System.out.println("Escolha o tipo do jogador:");
            System.out.println("1 - Sortudo:");
            System.out.println("2 - Normal:");
            System.out.println("3 - Azarado:");
            System.out.print("-> ");
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
                    System.out.println("Tipo inválido, tente novamente");
            }
            indice++; 
        }
        return tipo;
    }

    public void configTabuleiro(int numCasas) {
        FactoryCasa factoryCasa = new FactoryCasa();
        tabuleiro = Tabuleiro.getInstancia();
        for(int i =0; i < numCasas; i++){
            System.out.println("Escolha o tipo da casa " + (i + 1) + ":");
            System.out.println("1 - Casa Surpresa");
            System.out.println("2 - Casa Normal");
            System.out.println("3 - Casa Azar");
            System.out.println("4 - Casa Sorte");
            System.out.println("5 - Casa Reversa");
            System.out.println("6 - Casa Prisão");
            System.out.println("7 - Casa Jogar de Novo");
            System.out.print("-> ");
            int escolha = Teclado.nextInt();
            tabuleiro.adicionarCasa(factoryCasa.fazerCasa(escolha));
        }
    }

    public void printTabuleiro(){
        
    }

    public void startJogo(){
        Modo modoJogo = new Modo();
        int modo = modoJogo.getModo();
        int numJogadores = getNumJogadores();
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
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