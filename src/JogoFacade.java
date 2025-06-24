package src;

import java.util.Scanner;

public class JogoFacade {
    private static Tabuleiro tabuleiro; 
    private static Scanner Teclado = Entrada.getScanner();
    
    public int GetNumCasa() {
        System.out.print("Digite o número de casas do tabuleiro: ");
        int numCasas = Teclado.nextInt();
        Teclado.nextLine(); // Limpar o buffer do scanner
        if(numCasas < 9 || numCasas > ) {
            System.out.println("Número de casas inválido! Deve ser entre 10 e 100.");
            return GetNumCasa(); // Chama recursivamente até obter um valor válido
        }
        return numCasas;
    }

    public boolean configTabuleiro(){

    }

    public boolean config(){

    }

    public boolean printTabuleiro(){
        
    }

    public boolean startJogo(){
        // Lógica para iniciar o jogo
        return true; // Retorna true se o jogo foi iniciado com sucesso
    }
}