package src;

import java.util.Scanner;

public class Modo {
    private Scanner Teclado = Entrada.getScanner();

    private int modo;


    public int getModo() {
        System.out.println("Você deseja jogar no modo normal ou Debug? \n1 - Normal\n2 - Debug");
        int modo = Teclado.nextInt();
        Teclado.nextLine();
        switch (modo) {
            case 1:
                System.out.println("Você escolheu o modo normal.");
                break;
            case 2:
                System.out.println("Você escolheu o modo debug.");
                break;
            default:
                System.out.println("Modo inválido, escolha novamente.");
                return getModo();
        }
        return modo;
    }

    public int Debug(int rodada, Tabuleiro tabuleiro) {
        Scanner T = Entrada.getScanner();
        
        System.out.println("Você escolheu o modo debug.");
        System.out.println("No modo debug, você pode escolher a casa que o jogador irá andar.");
        System.out.println("Digite o número da casa (0-39) ou -1 para sair do modo debug.");
    	for (int i=0; i<tabuleiro.getJogadores().size(); i++) {
            if(tabuleiro.getJogadores().get(i).getPodeJogar()){
                System.out.println("\n\nRodada " + rodada);
                System.out.println("- - - VEZ DO JOGADOR " + tabuleiro.getJogadores().get(i).getCor().toUpperCase() + " - - -");
                System.out.println("Você deseja que o Jogador " + tabuleiro.getJogadores().get(i).getCor() + " ande até que casa? ");
                int casa = T.nextInt();
                int flagTabuleiro = tabuleiro.modoDebug(rodada, casa, i);
                if (flagTabuleiro != -1) {
                    return flagTabuleiro;
                }
            }else{
                tabuleiro.getJogadores().get(i).setPodeJogar(true);
                System.out.println("\n\nRodada " + rodada);
                System.out.println("\nJogador " + tabuleiro.getJogadores().get(i).getCor() + " não pode jogar nesta rodada.");   
            } 
    	}
    	return -1;
    }

    public int Normal(int rodada, Tabuleiro tabuleiro) {
        for (int i=0; i<tabuleiro.getJogadores().size(); i++) {
            
            if (tabuleiro.getJogadores().get(i).getPodeJogar()) {
                System.out.println("\n\nRodada " + rodada);
                System.out.println("- - - VEZ DO JOGADOR " + tabuleiro.getJogadores().get(i).getCor().toUpperCase() + " - - -");

                System.out.println("Vez do jogador "+ tabuleiro.getJogadores().get(i).getCor());
                System.out.println("Role os dados!");

                System.out.println("Pressione 1 - para rolar os dados 2 - Passar Rodada.");
                int esc = Teclado.nextInt();
                if (esc == 2) {
                    System.out.println("Rodada passada.");
                    continue;
                }else if(esc == 1){
                    System.out.println("Rolando os dados...");

                }
                int resultado = tabuleiro.getJogadores().get(i).rolarDados();
                int flagTabuleiro = tabuleiro.fazerRodada(resultado, i);    		
                if (flagTabuleiro != -1) {
                    return flagTabuleiro;
                }
            } else {
                tabuleiro.getJogadores().get(i).setPodeJogar(true);
                System.out.println("\n\nRodada " + rodada);
                System.out.println("\nJogador " + tabuleiro.getJogadores().get(i).getCor() + " não pode jogar nesta rodada.");
            } 
    	}
    	return -1;
    }

}
