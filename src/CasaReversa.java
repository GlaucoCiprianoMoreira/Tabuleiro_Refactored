package src;
public class CasaReversa extends Casa {
    @Override
    public String aplicarEfeito(Jogador jogador) {
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();

        int indice = jogador.getIndice();
        int casaAtual = tabuleiro.getCasaJogador(indice);
        int menorCasa = casaAtual; // comparar com a casa atual do jogador
        int indiceMenorCasa = -1; // -1 indica que não há ninguém atrás
    
        for(int j = 0; j < tabuleiro.getJogadores().size(); j++){
            if(j != jogador.getIndice()){
                Jogador outroJogador = tabuleiro.getJogadores().get(j);
                int indice2 = outroJogador.getIndice();
                int casaOutroJogador = tabuleiro.getCasaJogador(indice2);
                if(casaOutroJogador < menorCasa){
                    menorCasa = casaOutroJogador;
                    indiceMenorCasa = j;
                }
            }
        }
        if (indiceMenorCasa != -1) {
            tabuleiro.setCasaJogador(indice, menorCasa);
            tabuleiro.setCasaJogador(indiceMenorCasa, casaAtual);
            return "Jogador trocou de lugar com o que está mais atrás.";
        } else {
            return "Não há jogadores atrás para trocar de lugar.";
        }
    }
    
}
