package src;
public class CasaReversa extends Casa {
    @Override
    public String aplicarEfeito(Jogador jogador) {
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();

        int indiceJogadorAtual = tabuleiro.getJogadores().indexOf(jogador);
        int casaAtual = tabuleiro.getCasaJogador(indiceJogadorAtual);
        int menorCasa = Integer.MAX_VALUE;
        int indiceJogadorMenorCasa = -1;

        for (int idx = 0; idx < tabuleiro.getJogadores().size(); idx++) {
            if (idx != indiceJogadorAtual) {
                int casaOutro = tabuleiro.getCasaJogador(idx);
                if (casaOutro <= menorCasa) {
                    menorCasa = casaOutro;
                    indiceJogadorMenorCasa = idx;
                }
            }
        }

        if (indiceJogadorMenorCasa != -1 && menorCasa <= casaAtual) {
            tabuleiro.setCasaJogador(indiceJogadorAtual, menorCasa);
            tabuleiro.setCasaJogador(indiceJogadorMenorCasa, casaAtual);
            return "Jogador trocou de lugar com o que está mais atrás.";
        } else {
            return "Não há jogadores atrás para trocar de lugar.";
        }
    }
}