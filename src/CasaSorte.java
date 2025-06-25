package src;

public class CasaSorte extends Casa {
    @Override
    public String aplicarEfeito(Jogador jogador) {
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        tabuleiro.setCasaJogador(jogador.getIndice(), tabuleiro.getCasaJogador(jogador.getIndice()) + 3);
        return "Você tirou uma carta de sorte! Avance 3 casas.";
    }
    
}
