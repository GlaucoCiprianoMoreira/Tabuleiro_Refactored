package src;

import java.util.Scanner;

public class Modo {
    private Scanner Teclado = Entrada.getScanner();

    private static final Tela tela = new Tela();

    public int getModo() {
        tela.pedirModo();
        int modo = Teclado.nextInt();
        Teclado.nextLine();
        switch (modo) {
            case 1:
                tela.mostrarModo("normal");
                break;
            case 2:
                tela.mostrarModo("debug");
                break;
            default:
                tela.mostrarErro("modo inválido, escolha novamente.");
                return getModo();
        }
        return modo;
    }

    public int jogarDebug(int rodada, Tabuleiro tabuleiro) {
        for (int i = 0; i < tabuleiro.getJogadores().size(); i++) {
            Jogador jogador = tabuleiro.getJogadores().get(i);

            if (jogador.getPodeJogar()) {
                incrementarJogadas(jogador);
                tela.rodadaJogadorDebug(rodada, jogador);

                int casa = obterCasaDebug(tabuleiro);
                if (casa == -1) return -2;

                tabuleiro.setCasaJogador(i, casa);
                tela.mostrarTabuleiroAposRodada(tabuleiro, casa, rodada, jogador);
                int zeroParaContinuar = Teclado.nextInt();
                while (zeroParaContinuar != 0) {
                    tela.mostrarTabuleiroAposRodada(tabuleiro, casa, rodada, jogador);
                    zeroParaContinuar = Teclado.nextInt();
                }

                if (casa == tabuleiro.getCasas().size()) {
                    tela.jogadorVenceu(jogador);
                    return i;
                }
            } else {
                tela.jogadorNaoPodeJogar(rodada, jogador);
            }
        }
        return -1;
    }

    public int jogarNormal(int rodada, Tabuleiro tabuleiro) {
        for (int i = 0; i < tabuleiro.getJogadores().size(); i++) {
            Jogador jogador = tabuleiro.getJogadores().get(i);

            if (jogador.getPodeJogar()) {
                incrementarJogadas(jogador);
                tela.rodadaJogadorNormal(rodada, jogador);

                int esc = Teclado.nextInt();
                if (esc == 2) {
                    tela.rodadaPassadaNormal(rodada, jogador);
                } else if (esc == 1) {
                    int novaPosicao = rolarEDeterminarNovaPosicao(tabuleiro, i, jogador);
                    tela.mostrarTabuleiroAposRodada(tabuleiro, novaPosicao, rodada, jogador);
                    int zeroParaContinuar = Teclado.nextInt();
                    while (zeroParaContinuar != 0) {
                        tela.mostrarTabuleiroAposRodada(tabuleiro, novaPosicao, rodada, jogador);
                        zeroParaContinuar = Teclado.nextInt();
                    }

                    if (novaPosicao == tabuleiro.getCasas().size() - 1) {
                        tela.jogadorVenceu(jogador);
                        return i;
                    }
                }
            } else {
                tela.jogadorNaoPodeJogar(rodada, jogador);
            }
        }
        return -1;
    }

    // Métodos auxiliares extraídos

    private void incrementarJogadas(Jogador jogador) {
        jogador.setNumJogadas(jogador.getNumJogadas() + 1);
    }

    private int obterCasaDebug(Tabuleiro tabuleiro) {
        int casa;
        while (true) {
            tela.casaDesejadaDebug();
            casa = Teclado.nextInt();
            if (casa == -1) {
                tela.mensagemSimples("Saindo...");
                return -1;
            }
            if (casa >= 0 && casa <= tabuleiro.getCasas().size()) {
                return casa;
            } else {
                tela.casaInvalidaDebug(tabuleiro);
            }
        }
    }

    private int rolarEDeterminarNovaPosicao(Tabuleiro tabuleiro, int i, Jogador jogador) {
        tela.rolandoDados();
        jogador.getDado1().rolar();
        jogador.getDado2().rolar();
        int resultado = jogador.getDado1().getValor() + jogador.getDado2().getValor();
        tela.resultadoDados(resultado);

        int novaPosicao = tabuleiro.getCasaJogador(i) + resultado;
        int ultimaCasa = tabuleiro.getCasas().size() - 1;
        if (novaPosicao > ultimaCasa) {
            novaPosicao = ultimaCasa;
        }
        tabuleiro.setCasaJogador(i, novaPosicao);
        return novaPosicao;
    }
}
