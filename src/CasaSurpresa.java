package src;

import java.util.Random;

public class CasaSurpresa extends Casa {
    public void aplicarEfeito(Jogador jogador){
        Random random = new Random();
        int tipo = random.nextInt(3) + 1;
        System.out.println("\nCarta sorteada:" + tipo);
        switch (tipo) {
            case 1:
                tabuleiro.getJogadores().set(jogador.getIndice(), new Normal(tabuleiro.getJogadores().get(jogador.getIndice()).getCor(), jogador.getIndice()));
                System.out.println("Jogador agora é normal.\n");
                break;
            case 2:
                tabuleiro.getJogadores().set(jogador.getIndice(), new Azarado(tabuleiro.getJogadores().get(jogador.getIndice()).getCor(), jogador.getIndice()));
                System.out.println("Jogador agora é azarado.\n");
                break;
            case 3:
                tabuleiro.getJogadores().set(jogador.getIndice(), new Sortudo(tabuleiro.getJogadores().get(jogador.getIndice()).getCor(), jogador.getIndice()));
                System.out.println("Jogador agora é sortudo.\n");
                break;
        }
    }
}

