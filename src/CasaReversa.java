package src;
public class CasaReversa extends Casa {
    @Override
    public String aplicarEfeito(Jogador jogador) {
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();

        int indiceJogadorAtual = jogador.getIndice();
        int casaAtual = tabuleiro.getCasaJogador(indiceJogadorAtual);
        int menorCasa = Integer.MAX_VALUE;
        int indiceMenorCasa = -1;
    
        System.out.println("----- DEPURAÇÃO CasaReversa -----");
        System.out.println("Jogador atual: índice=" + indiceJogadorAtual + ", casa=" + casaAtual);

        for (Jogador outroJogador : tabuleiro.getJogadores()) {
            int indiceOutro = outroJogador.getIndice();
            System.out.print("-> jogador " + indiceOutro);
            if (indiceOutro != indiceJogadorAtual) {
                int casaOutro = tabuleiro.getCasaJogador(indiceOutro);
                System.out.println("-> Jogador " + indiceOutro + ": casa=" + casaOutro);

                if (casaOutro <= menorCasa) {  // <= para incluir jogadores na mesma posição
                    menorCasa = casaOutro;
                    indiceMenorCasa = indiceOutro;
                }
            }
        }

        System.out.println("Resultado: menorCasa=" + menorCasa + ", indiceMenorCasa=" + indiceMenorCasa);
        
        if (indiceMenorCasa != -1 && menorCasa <= casaAtual) {  // <= para permitir troca com quem está na mesma posição
            tabuleiro.setCasaJogador(indiceJogadorAtual, menorCasa);
            tabuleiro.setCasaJogador(indiceMenorCasa, casaAtual);
            return "Jogador trocou de lugar com o que está mais atrás.";
        } else {
            return "Não há jogadores atrás para trocar de lugar.";
        }
    }
}