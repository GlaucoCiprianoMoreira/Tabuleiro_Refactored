package src;

public class CasaSorte extends Casa {
    @Override
    public String aplicarEfeito(Jogador jogador) {
        if (jogador instanceof Azarado) {
            return "Jogador azarado não pode tirar cartas de sorte.";
        }
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        int indiceReal = tabuleiro.getJogadores().indexOf(jogador);
        int novaPosicao = tabuleiro.getCasaJogador(indiceReal) + 3;
        int ultimaCasa = tabuleiro.getCasas().size();
        if (novaPosicao > ultimaCasa) {
            novaPosicao = ultimaCasa;
        }
        tabuleiro.setCasaJogador(indiceReal, novaPosicao);
        return "Você tirou uma carta de sorte! Avance 3 casas.";
    }
    
}
