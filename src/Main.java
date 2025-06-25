package src;

import java.util.Scanner;


public class Main {
    private final Scanner Teclado = new Scanner(System.in);

    public static void main(String[] args){
        JogoFacade jogo = new JogoFacade();
        int numJogadores = jogo.getNumJogadores();
        int numCasas = jogo.getNumCasas();
        jogo.configTabuleiro(numCasas);
        jogo.config(numJogadores);
        jogo.startJogo();        
    }
    
}