package src;
public class CasaJogarDeNovo extends Casa {
    
    @Override
    public String aplicarEfeito(Jogador jogador ) {
        if (jogador instanceof Azarado) {
            return "Jogador azarado não pode jogar novamente.";

        }
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        int result =tabuleiro.getJogadores().get(jogador.getIndice()).rolarDados();
        return "Você caiu na casa de jogar novamente! Role os dados novamente. Tirou " + result + " no dado.\n";

    }    
}
