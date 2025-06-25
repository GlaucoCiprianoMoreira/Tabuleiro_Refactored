package src;
public class CasaAzar extends Casa {
    @Override
    public void aplicarEfeito(Jogador jogador) {
        tabuleiro.getCasas(jogador.getIndice()) = tabuleiro.getCasas(jogador.getIndice()) - 3;
        System.out.println("Você tirou uma carta de azar! Volte 3 casas.");
    }
}
