package src;
public class CasaAzar extends Casa {
    @Override
    public String aplicarEfeito(Jogador jogador) {
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        tabuleiro.setCasaJogador(jogador.getIndice(), tabuleiro.getCasaJogador(jogador.getIndice()) - 3);
        return "Você tirou uma carta de azar! Volte 3 casas.";
    }
}
