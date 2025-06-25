package src;

import javax.print.attribute.standard.JobMessageFromOperator;

public class CasaSorte extends Casa {
    @Override
    public void aplicarEfeito(Jogador jogador) {
        tabuleiro.getCasas(jogador.getIndice()) = tabuleiro.getCasas(jogador.getIndice()) + 3;
        System.out.println("Você tirou uma carta de sorte! Avance 3 casas.");
    }
    
}
