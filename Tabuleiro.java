import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Tabuleiro{
    private ArrayList<Jogador> jogadores;
    private int numJogadores;
    private Scanner T = Entrada.getScanner();

    public Tabuleiro(int numJogadores){
        this.jogadores = new ArrayList<>();
        this.numJogadores = numJogadores;
    }

    public ArrayList<Jogador> getJogadores(){
        return jogadores;
    }

    public void adicionarJogadores(Jogador j){
        jogadores.add(j);
    }

    private void mostrarFimdeRodada(){
        for(Jogador jogador : jogadores){
            System.out.println("Jogador: " + jogador.getCor() + " | Casa atual: " + jogador.getCasaAtual());
        }
    }
    public int fazerRodada(int resultado, int i) {

        
            int incremento = jogadores.get(i).getNumJogadas() + 1;
            jogadores.get(i).setNumJogadas(incremento);

            System.out.println("Resultado dos dados: " + resultado);

            jogadores.get(i).somaCasaAtual(resultado);
            int casaAtual = jogadores.get(i).getCasaAtual();

            if (casaAtual >= 40) {
                mostrarFimdeRodada();
                return i;
            } else if (casaAtual == 10 || casaAtual == 25 || casaAtual == 38) {
                jogadores.get(i).setPodeJogar(false);
            } else if (casaAtual == 20 || casaAtual == 35) {
                trocarDeCasa(i);
            } else if (casaAtual == 5 || casaAtual == 15 || casaAtual == 30) {
                if (jogadores.get(i) instanceof Normal || jogadores.get(i) instanceof Sortudo) {
                    System.out.println("Jogador anda mais 3 casas.");
                    jogadores.get(i).somaCasaAtual(3);
                }
            } else if (casaAtual == 17 || casaAtual == 27) {
                voltarParaInicio(i);
            } else if (casaAtual == 13) {
                mudarTipoJogador(i);
            }

        

        if (jogadores.get(i).getDado1() == jogadores.get(i).getDado2()) {
            System.out.println("O jogador " + jogadores.get(i).getCor() + " ganhou mais uma rodada!");
            int resultado2 = jogadores.get(i).rolarDados();
            return fazerRodada(resultado2, i); // chamada recursiva com retorno
        }

        mostrarFimdeRodada();

        return -1;
    }

    
    public int modoDebug(int rodada, int casa, int i) {
        System.out.println("\n\nRodada " + rodada);
        System.out.println("- - - VEZ DO JOGADOR " + jogadores.get(i).getCor().toUpperCase() + " - - -");
        
        jogadores.get(i).setCasaAtual(casa);

    
        int casaAtual = casa;
        int incremento = jogadores.get(i).getNumJogadas() + 1;
        jogadores.get(i).setNumJogadas(incremento); 
        if (casaAtual >= 40){
            mostrarFimdeRodada();
            return i;
        }else if (casaAtual == 10 || casaAtual == 25 || casaAtual == 38) {
            jogadores.get(i).setPodeJogar(false);
        }else if (casaAtual == 20 || casaAtual == 35) {
            trocarDeCasa(i);
        }
        else if (casaAtual == 5 || casaAtual == 15 || casaAtual == 30){
            if (jogadores.get(i) instanceof Normal || jogadores.get(i) instanceof Sortudo){
                System.out.println("Jogador anda mais 3 casas.");
                jogadores.get(i).somaCasaAtual(3);   
            }
        }else if(casaAtual == 17 || casaAtual == 27){
            voltarParaInicio(i);
        }else if (casaAtual == 13) {
            mudarTipoJogador(i);
        }
            
        
        mostrarFimdeRodada();
        return -1;
    }
    
    private void trocarDeCasa(int i){
        int casaAtual = jogadores.get(i).getCasaAtual();
        int menorCasa = casaAtual; // comparar com a casa atual do jogador
        int indiceMenorCasa = -1; // -1 indica que não há ninguém atrás
    
        for(int j = 0; j < numJogadores; j++){
            if(j != i){
                int casaOutroJogador = jogadores.get(j).getCasaAtual();
                if(casaOutroJogador < menorCasa){
                    menorCasa = casaOutroJogador;
                    indiceMenorCasa = j;
                }
            }
        }
        if (indiceMenorCasa != -1) {
            jogadores.get(i).setCasaAtual(menorCasa);
            jogadores.get(indiceMenorCasa).setCasaAtual(casaAtual);
            System.out.println("Jogador " + jogadores.get(i).getCor() + " trocou de casa com o jogador " + jogadores.get(indiceMenorCasa).getCor());
            System.out.println("Jogador " + jogadores.get(i).getCor() + " está na casa " + jogadores.get(i).getCasaAtual());
        } else {
            System.out.println("Jogador " + jogadores.get(i).getCor() + " está em último e não trocou de casa.");
        }
    }

    private void voltarParaInicio(int i){
        System.out.println("\n"+jogadores.get(i).getCor() + "pode escolher:");
        System.out.println("Escolha o jogador que você deseja que volte para o início:");

        for(int j = 0; j< numJogadores; j++){
            if(j != i){
                System.out.println((j+1) + "-" + jogadores.get(j).getCor());
            }
        }

        
        int esc = T.nextInt();
        esc--;

        while(esc < 0 || esc > numJogadores || esc == i){
            System.out.print("Escolha uma cor válida: ");
            esc = T.nextInt();
            esc--;
        }

        jogadores.get(esc).setCasaAtual(0);
        System.out.println("Jogador " + jogadores.get(esc).getCor() + " voltou para o início. \n");
    }
    
    private void mudarTipoJogador(int i){
        Random random = new Random();
        int tipo = random.nextInt(3) + 1;
        System.out.println("\n\nCarta sorteada:" + tipo);
        switch (tipo) {
            case 1:
                jogadores.set(i, new Normal(jogadores.get(i).getCor(), jogadores.get(i).getCasaAtual()));
                System.out.println("Jogador " + jogadores.get(i).getCor() + " agora é Normal.");
                break;
            case 2:
                jogadores.set(i, new Azarado(jogadores.get(i).getCor(), jogadores.get(i).getCasaAtual()));
                System.out.println("Jogador " + jogadores.get(i).getCor() + " agora é Azarado");
                break;
            case 3:
                jogadores.set(i, new Sortudo(jogadores.get(i).getCor(), jogadores.get(i).getCasaAtual()));
                System.out.println("Jogador " + jogadores.get(i).getCor() + " agora é sortudo");
                break;
        }
    }
    
}