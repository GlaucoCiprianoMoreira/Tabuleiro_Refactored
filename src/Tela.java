package src;

public class Tela {
    private void limparTela(){
        try {
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Não foi possível limpar a tela.");
        }
    }

    private void darDelay(int tempo){
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
            System.out.print("Erro: delay interrompido!");
        }
    }

    private void titulo(String nome){
        System.out.println("=".repeat(nome.length() + 8));
        System.out.println("~~~ " + nome.toUpperCase() + " ~~~");
        System.out.println("=".repeat(nome.length() + 8) + "\n");
    }

    public void mostrarErro(String erro){
        System.out.print("Erro: " + erro);
        darDelay(2000);
    }

    public void saidaGeral(String mensagem){
        limparTela();
        System.out.println(mensagem);
        darDelay(2000);
    }

    public void pedirNumJogadores(){
        limparTela();
        titulo("configurando");
        
        System.out.print("Digite o número de jogadores [ 2 - 6 ]\n-> ");
    }

    public void pedirNumCasas(){
        limparTela();
        titulo("configurando");

        System.out.print("Digite o número de casas do tabuleiro [ 10 - 100 ]\n-> ");
    }

    public void pedirTipoCasa(int casa){
        limparTela();
        titulo("configurando casa " + casa);

        System.out.println("Escolha o tipo da casa " + casa + ":");
        System.out.println("1 - Casa Surpresa");
        System.out.println("2 - Casa Normal");
        System.out.println("3 - Casa Azar");
        System.out.println("4 - Casa Sorte");
        System.out.println("5 - Casa Reversa");
        System.out.println("6 - Casa Prisão");
        System.out.println("7 - Casa Jogar de Novo");
        System.out.print("-> ");
    }

    public void pedirCorJogador(int jogador){
        limparTela();
        titulo("cofigurando jogador " + jogador);

        System.out.println("ESCOLHA COR DO JOGADOR " + jogador + ":");
        System.out.println("1 - Azul");
        System.out.println("2 - Verde");    
        System.out.println("3 - Amarelo");
        System.out.println("4 - Laranja");
        System.out.println("5 - Vermelho");
        System.out.println("6 - Rosa");
        System.out.print("-> ");
    }

    public void pedirTipoJogador(String cor){
        System.out.println("\n=================================================\n");
        System.out.println("ESCOLHA O TIPO DE JOGADOR DO " + cor.toUpperCase() + ":");
        System.out.println("1 - Sortudo:");
        System.out.println("2 - Normal:");
        System.out.println("3 - Azarado:");
        System.out.print("-> ");
    }

    public void mostrarIniciandoJogo(){
        limparTela();
        titulo("tudo configurado");
        darDelay(500);

        for(int i = 0; i <= 10; i++){
            limparTela();
            titulo("iniciando jogo");

            int tempoDelay;
            switch(i){
                case 0:
                    tempoDelay = 200;
                    break;
                case 1:
                    tempoDelay = 1000;
                    break;
                case 9:
                    tempoDelay = 3000;
                    break;
                case 10:
                    tempoDelay = 100;
                    break;
                default:
                    tempoDelay = 20;
                    break;
            }

            System.out.print("+---------------------------------------+\n|");
            for(int j = 1; j <= 10; j++){
                System.out.print(j <= i ? "III|" : "   |");
            }
            System.out.println(" ~ " + (i * 10) + "%");
            System.out.println("+---------------------------------------+");
            
            darDelay(tempoDelay);
        }
    }

    public void pedirModo(){
        limparTela();
        titulo("escolha do modo");
        
        System.out.println("ESCOLHA O MODO DE JOGO");
        System.out.println("1 - Normal");
        System.out.println("2 - Debug");
        System.out.print("-> ");
    }

    public void mostrarModo(String modo){
        System.out.println("\n=================================================\n");
        System.out.println("Modo escolhido: " + modo.toUpperCase());
        darDelay(1000);
    }

    public void finalJogo(int  jogadorVitorioso, int rodada, Tabuleiro tabuleiro){
        limparTela();
        titulo("fim de jogo");
        System.out.println("\nRodada " + rodada + " - Fim de jogo!");
        System.out.println("Jogador " + tabuleiro.getJogadores().get(jogadorVitorioso).getCor() + " vitorioso.");
        System.out.println("Número de jogadas dos jogadores:");
        for (int j=0; j<tabuleiro.getJogadores().size(); j++) {
        	System.out.println("Número de jogadas do jogador " + tabuleiro.getJogadores().get(j).getCor() + " é " + tabuleiro.getJogadores().get(j).getNumJogadas());
        }
    }

    public void mostrarTabuleiro(int nCasas){

    }

    public void pedirAcaoJogador(String modo, String corJogador){
        limparTela();
        titulo(modo + " - jogador " + corJogador);
    }
}
