package src;
public class CasaReversa extends Casa {
    @Override
    public void aplicarEfeito(Jogador jogador) {
        int indice = jogador.getIndice();
        int casaAtual = tabuleiro.getCasas(indice);
        int menorCasa = casaAtual; // comparar com a casa atual do jogador
        int indiceMenorCasa = -1; // -1 indica que não há ninguém atrás
    
        for(int j = 0; j < tabuleiro.getJogadores().size(); j++){
            if(j != jogador.getIndice()){
                Jogador outroJogador = tabuleiro.getJogadores().get(j);
                int indice2 = outroJogador.getIndice();
                int casaOutroJogador = tabuleiro.getCasas(indice2);
                if(casaOutroJogador < menorCasa){
                    menorCasa = casaOutroJogador;
                    indiceMenorCasa = j;
                }
            }
        }
        if (indiceMenor != -1) {
            Tabuleiro.setPosicaoJogador(indiceJogador, menorPosicao);
            Tabuleiro.setPosicaoJogador(indiceMenor, posicaoAtual);
            System.out.println("Jogador trocou de lugar com o que está mais atrás.");
        } else {
            System.out.println("Não há jogadores atrás para trocar de lugar.");
        }
    }
    
}
