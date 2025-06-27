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

    public int Debug(int rodada, Tabuleiro tabuleiro) {
        tela.inicioDebug(tabuleiro);

        for (int i = 0; i < tabuleiro.getJogadores().size(); i++) {
            Jogador jogador = tabuleiro.getJogadores().get(i);
            tela.rodadaJogadorDebug(rodada, jogador);

            if (jogador.getPodeJogar()) {
                int incremento = jogador.getNumJogadas() + 1;
                jogador.setNumJogadas(incremento);

                tela.rodadaJogadorDebug(rodada, jogador);

                int casa = -2;
                while (true) {
                    tela.casaDesejadaDebug();
                    casa = Teclado.nextInt();

                    if (casa == -1) {
                        tela.saindoDebug();
                        return -1;
                    }

                    if (casa >= 0 && casa < tabuleiro.getCasas().size()) {
                        break;
                    } else {
                        tela.casaInvalidaDebug(tabuleiro);
                    }
                }

                // Atualizar posição e aplicar efeito
                tabuleiro.setCasaJogador(i, casa);
                String mensagem = tabuleiro.getCasas().get(casa).aplicarEfeito(jogador);
                tela.mensagemDebug(mensagem);
                tela.posicaoJogador(jogador, casa);

                // Verificar se jogador venceu
                if (casa == tabuleiro.getCasas().size() - 1) {
                    tela.jogadorVenceu(jogador);
                    return i; // retorna o índice do jogador vencedor, ou use outro valor especial se preferir
                }

            } else {
                int pos = tabuleiro.getCasaJogador(i);
                String mensagem = tabuleiro.getCasas().get(pos).aplicarEfeito(jogador);
                tela.jogadorNaoPodeJogar(rodada, mensagem);
            }
        }

        return -1; 
    }

    public int Normal(int rodada, Tabuleiro tabuleiro) {
        int resultado = 0;
        tela.inicioNormal();

        for (int i = 0; i < tabuleiro.getJogadores().size(); i++) {
            Jogador jogador = tabuleiro.getJogadores().get(i);
            tela.rodadaJogadorNormal(rodada, jogador);

            if (jogador.getPodeJogar()) {
                int incremento = jogador.getNumJogadas() + 1;
                jogador.setNumJogadas(incremento);

                tela.rodadaJogadorNormal(rodada, jogador);
                int esc = Teclado.nextInt();

                if (esc == 2) {
                    tela.rodadaPassadaNormal();
                    continue;
                } else if (esc == 1) {
                    tela.rolandoDados();
                    jogador.getDado1().rolar();
                    jogador.getDado2().rolar();
                    resultado = jogador.getDado1().getValor() + jogador.getDado2().getValor();
                    tela.resultadoDados(resultado);

                    int novaPosicao = tabuleiro.getCasaJogador(i) + resultado;
                    int ultimaCasa = tabuleiro.getCasas().size() - 1;
                    if (novaPosicao > ultimaCasa) {
                        novaPosicao = ultimaCasa;
                    }

                    tabuleiro.setCasaJogador(i, novaPosicao);
                    String mensagem = tabuleiro.getCasas().get(novaPosicao).aplicarEfeito(jogador);
                    tela.saidaGeral(mensagem);
                    tela.posicaoJogador(jogador, novaPosicao);

                    if (novaPosicao == ultimaCasa) {    
                        tela.jogadorVenceu(jogador);
                        return i;
                    }
                }

            } else {
                int pos = tabuleiro.getCasaJogador(i);
                String mensagem = tabuleiro.getCasas().get(pos).aplicarEfeito(jogador);
                tela.jogadorNaoPodeJogar(rodada, mensagem);
            }
        }

        return -1;
    }



}
