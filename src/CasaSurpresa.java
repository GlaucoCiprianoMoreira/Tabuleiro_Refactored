package src;

import java.util.Random;

public class CasaSurpresa extends Casa {
    public String aplicarEfeito(Jogador jogador){
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        Random random = new Random();
        int tipo = random.nextInt(3) + 1;
        Tela.casaSurpresaTela(tipo);
        switch (tipo) {
            case 1:
                tabuleiro.getJogadores().set(jogador.getIndice(), new Normal(tabuleiro.getJogadores().get(jogador.getIndice()).getCor(), jogador.getIndice()));
                return "Jogador agora é normal.\n";
            case 2:
                tabuleiro.getJogadores().set(jogador.getIndice(), new Azarado(tabuleiro.getJogadores().get(jogador.getIndice()).getCor(), jogador.getIndice()));
                return "Jogador agora é azarado.\n";
            case 3:
                tabuleiro.getJogadores().set(jogador.getIndice(), new Sortudo(tabuleiro.getJogadores().get(jogador.getIndice()).getCor(), jogador.getIndice()));
                return "Jogador agora é sortudo.\n";
             
        }
        return null;
    }
}

