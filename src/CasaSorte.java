package src;

public class CasaSorte extends Casa {
    @Override
    public String aplicarEfeito(Jogador jogador) {
        if (jogador instanceof Azarado) {
            return "Jogador azarado não pode tirar cartas de sorte.";
        }
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        tabuleiro.setCasaJogador(jogador.getIndice(), tabuleiro.getCasaJogador(jogador.getIndice()) + 3);
        return "Você tirou uma carta de sorte! Avance 3 casas.";
    }
    
}
