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
        tela.saidaGeral("Você escolheu o modo debug.");
        tela.saidaGeral("No modo debug, você pode escolher a casa que o jogador irá andar.");
        tela.saidaSimples("Digite o número da casa (entre 0 e "); tela.saidaVariavel(String.valueOf(tabuleiro.getCasas().size() - 1)); tela.saidaGeral(") ou -1 para sair do modo debug.");


        for (int i = 0; i < tabuleiro.getJogadores().size(); i++) {
            Jogador jogador = tabuleiro.getJogadores().get(i);

            if (jogador.getPodeJogar()) {
                tela.saidaSimples("\n\nRodada "); tela.saidaVariavel(String.valueOf(rodada)); tela.saidaGeral("");
                tela.saidaSimples("- - - VEZ DO JOGADOR "); tela.saidaVariavel(jogador.getCor().toUpperCase()); tela.saidaGeral("");

                int casa = -2;
                while (true) {
                    tela.saidaGeral("Digite o número da casa desejada (ou -1 para sair): ");
                    casa = Teclado.nextInt();

                    if (casa == -1) {
                        tela.saidaGeral("Saindo do modo debug...");
                        return -1;
                    }

                    if (casa >= 0 && casa < tabuleiro.getCasas().size()) {
                        break;
                    } else {
                        tela.saidaSimples("Casa inválida! Digite um valor entre 0 e "); tela.saidaVariavel(String.valueOf(tabuleiro.getCasas().size() - 1)); tela.saidaGeral(".");
                    }
                }

                // Atualizar posição e aplicar efeito
                tabuleiro.setCasaJogador(i, casa);
                String mensagem = tabuleiro.getCasas().get(casa).aplicarEfeito(jogador);
                tela.saidaGeral(mensagem);

                // Verificar se jogador venceu
                if (casa == tabuleiro.getCasas().size() - 1) {
                    tela.saidaSimples("Jogador "); tela.saidaVariavel(jogador.getCor()); tela.saidaGeral(" venceu!");
                    return i; // retorna o índice do jogador vencedor, ou use outro valor especial se preferir
                }

            } else {
                int pos = tabuleiro.getCasaJogador(i);
                String mensagem = tabuleiro.getCasas().get(pos).aplicarEfeito(jogador);
                tela.saidaSimples("\n\nRodada "); tela.saidaVariavel(String.valueOf(rodada)); tela.saidaGeral("");
                tela.saidaGeral(mensagem);
            }
        }

        return -1; 
    }

    public int Normal(int rodada, Tabuleiro tabuleiro) {
        int resultado = 0;
        tela.saidaGeral("Você escolheu o modo normal.");

        for (int i = 0; i < tabuleiro.getJogadores().size(); i++) {
            Jogador jogador = tabuleiro.getJogadores().get(i);
            tela.saidaSimples("\n\nRodada "); tela.saidaVariavel(String.valueOf(rodada)); tela.saidaGeral(".");
            tela.saidaSimples("- - - VEZ DO JOGADOR "); tela.saidaVariavel(jogador.getCor().toUpperCase()); tela.saidaGeral(".");

            if (jogador.getPodeJogar()) {
                tela.saidaSimples("\n\nVez do jogador "); tela.saidaVariavel(jogador.getCor()); tela.saidaGeral(".");
                tela.saidaGeral("Pressione 1 para rolar os dados ou 2 para passar a rodada.");
                int esc = Teclado.nextInt();

                if (esc == 2) {
                    tela.saidaGeral("Rodada passada.");
                    continue;
                } else if (esc == 1) {
                    tela.saidaGeral("Rolando os dados...");
                    jogador.getDado1().rolar();
                    jogador.getDado2().rolar();
                    resultado = jogador.getDado1().getValor() + jogador.getDado2().getValor();
                    tela.saidaSimples("Resultado dos dados: "); tela.saidaVariavel(String.valueOf(resultado)); tela.saidaGeral(".");

                    int novaPosicao = tabuleiro.getCasaJogador(i) + resultado;
                    int ultimaCasa = tabuleiro.getCasas().size() - 1;
                    if (novaPosicao > ultimaCasa) {
                        novaPosicao = ultimaCasa;
                    }

                    tabuleiro.setCasaJogador(i, novaPosicao);
                    String mensagem = tabuleiro.getCasas().get(novaPosicao).aplicarEfeito(jogador);
                    tela.saidaGeral(mensagem);

                    if (novaPosicao == ultimaCasa) {
                        tela.saidaSimples("Jogador "); tela.saidaVariavel(jogador.getCor()); tela.saidaGeral(" venceu!");
                        return i;
                    }
                }

            } else {
                int pos = tabuleiro.getCasaJogador(i);
                String mensagem = tabuleiro.getCasas().get(pos).aplicarEfeito(jogador);
                tela.saidaSimples("\n\Rodada "); tela.saidaVariavel(String.valueOf(rodada)); tela.saidaGeral(".");
                tela.saidaGeral(mensagem);
            }
        }

        return -1;
    }



}
