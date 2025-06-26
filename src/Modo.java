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
        System.out.println("Você escolheu o modo debug.");
        System.out.println("No modo debug, você pode escolher a casa que o jogador irá andar.");
        System.out.println("Digite o número da casa ou -1 para sair do modo debug.");
    	for (int i=0; i<tabuleiro.getJogadores().size(); i++) {
            if(tabuleiro.getJogadores().get(i).getPodeJogar()){
                System.out.println("\n\nRodada " + rodada);
                System.out.println("- - - VEZ DO JOGADOR " + tabuleiro.getJogadores().get(i).getCor().toUpperCase() + " - - -");
                System.out.println("Você deseja que o Jogador " + tabuleiro.getJogadores().get(i).getCor() + " ande até que casa? ");
                int casa = Teclado.nextInt();
                String mensagem = tabuleiro.getCasas().get(casa).aplicarEfeito(tabuleiro.getJogadores().get(i));
                tabuleiro.setCasaJogador(i, casa);
                System.out.println(mensagem);
            }else{
                String mensagem = tabuleiro.getCasas().get(tabuleiro.getCasaJogador(i)).aplicarEfeito(tabuleiro.getJogadores().get(i));
                System.out.println("\n\nRodada " + rodada);
                System.out.println(mensagem);
                  
            } 
    	}
    	return -1;
    }

    public int Normal(int rodada, Tabuleiro tabuleiro) {
        int resultado = 0;
        System.out.println("Você escolheu o modo normal.");
        for (int i=0; i<tabuleiro.getJogadores().size(); i++) {  
            System.out.println("\n\nRodada " + rodada);
            System.out.println("- - - VEZ DO JOGADOR " + tabuleiro.getJogadores().get(i).getCor().toUpperCase() + " - - -");
            if(tabuleiro.getJogadores().get(i).getPodeJogar() == true){
                System.out.println("Vez do jogador "+ tabuleiro.getJogadores().get(i).getCor());

            System.out.println("Pressione 1 - para rolar os dados 2 - Passar Rodada.");
            int esc = Teclado.nextInt();
            if (esc == 2) {
                System.out.println("Rodada passada.");
                continue;
            }else if(esc == 1){
                System.out.println("Rolando os dados...");
                tabuleiro.getJogadores().get(i).getDado1().rolar();
                tabuleiro.getJogadores().get(i).getDado2().rolar();
                resultado = tabuleiro.getJogadores().get(i).getDado1().getValor() + tabuleiro.getJogadores().get(i).getDado2().getValor();
                System.out.println("Resultado dos dados: " + resultado);
            }
            tabuleiro.setCasaJogador(i, tabuleiro.getCasaJogador(i) + resultado);
            String mensagem =tabuleiro.getCasas().get(tabuleiro.getCasaJogador(i)).aplicarEfeito(tabuleiro.getJogadores().get(i));   
            System.out.println(mensagem);             
    	    }else{
                String mensagem = tabuleiro.getCasas().get(tabuleiro.getCasaJogador(i)).aplicarEfeito(tabuleiro.getJogadores().get(i));
                System.out.println("\n\nRodada " + rodada);
                System.out.println(mensagem);
            }
            if (tabuleiro.getCasaJogador(i) >= tabuleiro.getCasas().size() - 1) {
                System.out.println("Jogador " + tabuleiro.getJogadores().get(i).getCor() + " venceu!");
                return i; 
            }
        }
    	return -1;
    }


}
