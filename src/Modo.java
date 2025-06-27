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
        System.out.println("Você escolheu o modo debug.");
        System.out.println("No modo debug, você pode escolher a casa que o jogador irá andar.");
        System.out.println("Digite o número da casa (entre 0 e " + (tabuleiro.getCasas().size() - 1) + ") ou -1 para sair do modo debug.");

        for (int i = 0; i < tabuleiro.getJogadores().size(); i++) {
            Jogador jogador = tabuleiro.getJogadores().get(i);

            if (jogador.getPodeJogar()) {
                System.out.println("\n\nRodada " + rodada);
                System.out.println("- - - VEZ DO JOGADOR " + jogador.getCor().toUpperCase() + " - - -");

                int casa = -2;
                while (true) {
                    System.out.print("Digite o número da casa desejada (ou -1 para sair): ");
                    casa = Teclado.nextInt();

                    if (casa == -1) {
                        System.out.println("Saindo do modo debug...");
                        return -1;
                    }

                    if (casa >= 0 && casa < tabuleiro.getCasas().size()) {
                        break;
                    } else {
                        System.out.println("Casa inválida! Digite um valor entre 0 e " + (tabuleiro.getCasas().size() - 1));
                    }
                }

                // Atualizar posição e aplicar efeito
                tabuleiro.setCasaJogador(i, casa);
                String mensagem = tabuleiro.getCasas().get(casa).aplicarEfeito(jogador);
                System.out.println(mensagem);

                // Verificar se jogador venceu
                if (casa == tabuleiro.getCasas().size() - 1) {
                    System.out.println("Jogador " + jogador.getCor() + " venceu!");
                    return i; // retorna o índice do jogador vencedor, ou use outro valor especial se preferir
                }

            } else {
                int pos = tabuleiro.getCasaJogador(i);
                String mensagem = tabuleiro.getCasas().get(pos).aplicarEfeito(jogador);
                System.out.println("\n\nRodada " + rodada);
                System.out.println(mensagem);
            }
        }

        return -1; 
    }



    public int Normal(int rodada, Tabuleiro tabuleiro) {
        int resultado = 0;
        System.out.println("Você escolheu o modo normal.");

        for (int i = 0; i < tabuleiro.getJogadores().size(); i++) {
            Jogador jogador = tabuleiro.getJogadores().get(i);
            System.out.println("\n\nRodada " + rodada);
            tela.pedirAcaoJogador("normal", jogador.getCor());
            System.out.println("- - - VEZ DO JOGADOR " + jogador.getCor().toUpperCase() + " - - -");

            if (jogador.getPodeJogar()) {
                System.out.println("Vez do jogador " + jogador.getCor());
                System.out.println("Pressione 1 - para rolar os dados 2 - Passar Rodada.");
                int esc = Teclado.nextInt();

                if (esc == 2) {
                    System.out.println("Rodada passada.");
                    continue;
                } else if (esc == 1) {
                    System.out.println("Rolando os dados...");
                    jogador.getDado1().rolar();
                    jogador.getDado2().rolar();
                    resultado = jogador.getDado1().getValor() + jogador.getDado2().getValor();
                    System.out.println("Resultado dos dados: " + resultado);

                    int novaPosicao = tabuleiro.getCasaJogador(i) + resultado;
                    int ultimaCasa = tabuleiro.getCasas().size() - 1;
                    if (novaPosicao > ultimaCasa) {
                        novaPosicao = ultimaCasa;
                    }

                    tabuleiro.setCasaJogador(i, novaPosicao);
                    String mensagem = tabuleiro.getCasas().get(novaPosicao).aplicarEfeito(jogador);
                    System.out.println(mensagem);

                    if (novaPosicao == ultimaCasa) {
                        System.out.println("Jogador " + jogador.getCor() + " venceu!");
                        return i;
                    }
                }

            } else {
                int pos = tabuleiro.getCasaJogador(i);
                String mensagem = tabuleiro.getCasas().get(pos).aplicarEfeito(jogador);
                System.out.println("\n\nRodada " + rodada);
                System.out.println(mensagem);
            }
        }

        return -1;
    }



}
