package src;

public class Tela {
    private static final String RODADA = "\n\nRodada ";

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
        limparTela();
        System.out.println("=".repeat(nome.length() + 8));
        System.out.println("~~~ " + nome.toUpperCase() + " ~~~");
        System.out.println("=".repeat(nome.length() + 8) + "\n");
    }

    public void mostrarErro(String erro){
        System.out.print("Erro: " + erro);
        darDelay(2000);
    }

    public void saidaGeral(String mensagem){
        System.out.println(mensagem);
        darDelay(2000);
    }

    public void saidaSimples(String mensagem){
        System.out.print(mensagem + " ");
    }

    public void saidaVariavel(int variavel){
        System.out.print(String.valueOf(variavel) + " ");
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
        limparTela();
        titulo("configurando jogador " + cor);
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

    public void escolhaInvalidaModo(){
        limparTela();
        System.out.println("Escolha inválida! Digite 1 para modo normal ou 2 para modo debug.");
        darDelay(2000);
    }

    public void mostrarModo(String modo){
        System.out.println("\n=================================================\n");
        System.out.println("Modo escolhido: " + modo.toUpperCase());
        darDelay(1000);
    }

    public void inicioDebug(Tabuleiro tabuleiro){
        limparTela();
        System.out.println("Você escolheu o modo debug.");
        System.out.println("No modo debug, você pode escolher a casa que o jogador irá andar.");
        System.out.println("Digite o número da casa (entre 0 e " + (tabuleiro.getCasas().size() - 1) + ") ou -1 para sair do modo debug.");
    }

    public void inicioNormal(){
        limparTela();
        System.out.println("Você escolheu o modo normal.");
    }

    public void rodadaJogadorDebug(int rodada, Jogador jogador){
        limparTela();
        System.out.println(RODADA + rodada);
        System.out.println("- - - VEZ DO JOGADOR " + jogador.getCor().toUpperCase() + " - - -");
    }

    public void rodadaJogadorNormal(int rodada, Jogador jogador){
        limparTela();
        System.out.println(RODADA + rodada);
        System.out.println("- - - VEZ DO JOGADOR " + jogador.getCor().toUpperCase() + " - - -");
        System.out.println("Pressione 1 para rolar os dados ou 2 para passar a rodada.");
        System.out.print("-> ");
    }
    public void rodadaPassadaNormal(){
        limparTela();
        System.out.println("Rodada passada.");
        darDelay(2000);
    }

    public void rolandoDados(){
        System.out.println("Rolando os dados...");
        darDelay(2000);
    }

    public void resultadoDados(int resultado){
        System.out.println("Resultado dos dados: " + resultado);
        darDelay(2000);
    }

    public void jogadorNaoPodeJogar(int rodada, String mensagem){
        System.out.println(RODADA + rodada);
        System.out.println(mensagem);
    }

    public void casaDesejadaDebug(){
        System.out.print("Digite o número da casa desejada (ou -1 para sair): ");
    }

    public void saindoDebug(){
        System.out.println("Saindo do modo debug...");
        darDelay(2000);
    }

    public void casaInvalidaDebug(Tabuleiro tabuleiro){
        limparTela();
        System.out.println("Casa inválida! Digite um valor entre 0 e " + (tabuleiro.getCasas().size() - 1));
    }

    public void mensagemDebug(String mensagem){
        System.out.println(mensagem);
    }

    public void posicaoJogador(Jogador jogador, int posicao){
        System.out.println("Jogador " + jogador.getCor() + " está na casa " + posicao);
    }

    public void jogadorVenceu(Jogador jogador){
        System.out.println("Jogador " + jogador.getCor() + " venceu!");
    }
    
    private void mostrarLinha(Tabuleiro tabuleiro, int decrescente, int casasLinha, int casaInicial){
        System.out.println(" ".repeat(decrescente * (96 - (casasLinha * 4))) + "+---".repeat(casasLinha) + "+");

        //System.out.println(" ".repeat(decrescente * (96 - (casasLinha * 4))) + "|   ".repeat(casasLinha) + "|");
        
        if(decrescente == 0){
            for(int i = casaInicial; i < (casaInicial + casasLinha); i++){
                System.out.print("|");
                for(int j = 0; j < 3; j++){
                    if(tabuleiro.getJogadores().size() - 1 < j) {
                        System.out.print(" ");
                        continue;
                    }
                    if(tabuleiro.getCasaJogador(j) == i) System.out.print("P");
                    else System.out.print(" ");
                }
            }
            System.out.println("|");

            for(int i = casaInicial; i < (casaInicial + casasLinha); i++){
                if(i < 10) System.out.print("| " + i + " ");
                else if(i < 100) System.out.print("|" + (i / 10) + " " + (i % 10));
                else System.out.print("|" + i + "");
            }
            System.out.println("|");

            for(int i = casaInicial; i < (casaInicial + casasLinha); i++){
                System.out.print("|");
                for(int j = 2; j < 5; j++){
                    if(tabuleiro.getJogadores().size() - 1 < j) {
                        System.out.print(" ");
                        continue;
                    }
                    if(tabuleiro.getCasaJogador(j) == i) System.out.print("P");
                    else System.out.print(" ");
                }
            }
            System.out.println("|");
        }else{
            System.out.print(" ".repeat(96 - (casasLinha * 4)));
            for(int i = casasLinha; i > 0; i--){
                System.out.print("|");
                for(int j = 0; j < 3; j++){
                    if(tabuleiro.getJogadores().size() - 1 < j) {
                        System.out.print(" ");
                        continue;
                    }
                    if(tabuleiro.getCasaJogador(j) == i) System.out.print("P");
                    else System.out.print(" ");
                }
            }
            System.out.println("|");

            System.out.print(" ".repeat(96 - (casasLinha * 4)));
            for(int i = casasLinha; i > 0; i--){
                System.out.print("|" + (((casaInicial - 1) + i) / 10) + " " + (((casaInicial - 1) + i) % 10) + "");
            }
            System.out.println("|");

            System.out.print(" ".repeat(96 - (casasLinha * 4)));
            for(int i = casasLinha; i > 0; i--){
                System.out.print("|");
                for(int j = 2; j < 5; j++){
                    if(tabuleiro.getJogadores().size() - 1 < j) {
                        System.out.print(" ");
                        continue;
                    }
                    if(tabuleiro.getCasaJogador(j) == i) System.out.print("P");
                    else System.out.print(" ");
                }
            }
            System.out.println("|");
        }

        System.out.println(" ".repeat(decrescente * (96 - (casasLinha * 4))) + "+---".repeat(casasLinha) + "+");
    }

    private void mostrarCasaConexao(int x, int totalCasas, int casa){
        System.out.println(" ".repeat(92 * x) + "|   |");
        System.out.println(" ".repeat(92 * x) + "|" + (casa / 10) + " " + (casa % 10) + "|");
        System.out.println(" ".repeat(92 * x) + "|   |");
        if(totalCasas == casa) System.out.println(" ".repeat(92 * x) + "+---+");
    }

    private void mostrarTabuleiro(){
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();

        int numCasas = tabuleiro.getCasas().size();
        int casasPrimeiraLinha = Math.min(numCasas, 23) + 1;
        int casasTerceiraLinha = 0;
        if(numCasas > 24) casasTerceiraLinha = Math.min(numCasas - 24, 24);
        int casasQuintaLinha = 0;
        if(numCasas > 49) casasQuintaLinha = Math.min(numCasas - 49, 24);
        int casasSetimaLinha = 0;
        if(numCasas > 74) casasSetimaLinha = Math.min(numCasas - 74, 24);

        mostrarLinha(tabuleiro, 0, casasPrimeiraLinha, 0);

        if(numCasas >= 24) mostrarCasaConexao(1, numCasas, 24);
        else return;

        if(numCasas > 24) mostrarLinha(tabuleiro, 1, casasTerceiraLinha, 25);
        
        if(numCasas >= 49) mostrarCasaConexao(0, numCasas, 49);
        else return;

        if(numCasas > 49) mostrarLinha(tabuleiro, 0, casasQuintaLinha, 50);
        
        if(numCasas >= 74) mostrarCasaConexao(1, numCasas, 74);
        else return;

        if(numCasas > 74) mostrarLinha(tabuleiro, 1, casasSetimaLinha, 75);
        
        if(numCasas >= 99) mostrarCasaConexao(0, numCasas, 99);
        else return;

        if(numCasas == 100) mostrarLinha(tabuleiro, 0, 1, 100);
    }

    public void pedirAcaoJogador(String modo, String corJogador){
        limparTela();
        titulo(modo + " - jogador " + corJogador);

        mostrarTabuleiro();
    }

    public static void casaSurpresaTela(int tipo){
        System.out.println("\nCarta sorteada:" + tipo);
    }

    public void finalJogo(int  jogadorVitorioso, int rodada, Tabuleiro tabuleiro){
        titulo("fim de jogo");
        System.out.println(RODADA + rodada + " - Fim de jogo!");
        System.out.println("Jogador " + tabuleiro.getJogadores().get(jogadorVitorioso).getCor() + " vitorioso.");
        System.out.println("Número de jogadas dos jogadores:");
        for (int j=0; j<tabuleiro.getJogadores().size(); j++) {
        	System.out.println("Número de jogadas do jogador " + tabuleiro.getJogadores().get(j).getCor() + " é " + tabuleiro.getJogadores().get(j).getNumJogadas());
        }
    }
}
