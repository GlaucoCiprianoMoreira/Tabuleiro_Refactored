package src;

import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final Scanner Teclado = Entrada.getScanner();

    public static void main(String[] args){
        JogoFacade jogo = new JogoFacade();
        int numJogadores = jogo.getNumJogadores();
        int numCasas = jogo.getNumCasas();
        jogo.configTabuleiro(numCasas);
        jogo.config(numJogadores);
        jogo.startJogo();        
    }
    
}