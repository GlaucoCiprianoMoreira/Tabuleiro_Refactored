package src;
public class CasaAzar extends Casa {
    @Override
    public String aplicarEfeito(Jogador jogador) {
        if (jogador instanceof Sortudo) {
            return "Jogador sortudo não pode tirar cartas de azar.";
            
        }
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        int indiceReal = tabuleiro.getJogadores().indexOf(jogador);
        tabuleiro.setCasaJogador(indiceReal, tabuleiro.getCasaJogador(indiceReal) - 3);
        return "Você tirou uma carta de azar! Volte 3 casas.";
    }
}
