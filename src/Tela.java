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
        limparTela();
        System.out.println("=".repeat(nome.length() + 8));
        System.out.println("~~~ " + nome.toUpperCase() + " ~~~");
        System.out.println("=".repeat(nome.length() + 8) + "\n");
    }

    public void mostrarErro(String erro){
        System.out.print("Erro: " + erro);
        darDelay(2000);
    }

    public void mensagemSimples(String mensagem){
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

    public void pedirNumCasasEspeciais(){
        limparTela();
        titulo("configurando");

        System.out.print("Digite o número de casas especiais\n-> ");
    }

    public void pedirCasaEspecial(int iesima){
        limparTela();
        titulo("configurando");

        System.out.print("Digite a " + iesima + "º casa especial\n-> ");
    }

    public void pedirTipoCasa(int casa){
        limparTela();
        titulo("configurando casa " + casa);

        System.out.println("Escolha o tipo da casa " + casa + ":");
        System.out.println("1 - Casa Surpresa");
        System.out.println("2 - Casa Azar");
        System.out.println("3 - Casa Sorte");
        System.out.println("4 - Casa Reversa");
        System.out.println("5 - Casa Prisão");
        System.out.println("6 - Casa Jogar de Novo");
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
                    tempoDelay = 5;
                    break;
            }

            System.out.println("+---".repeat(10) + "+");
            for(int j = 1; j <= 10; j++){
                System.out.print(j <= i ? "| I " : "|   ");
            }
            System.out.println("| ~ " + (i * 10) + "%");
            System.out.println("+---".repeat(10) + "+");
            
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
    
    private void mostrarLinha(Tabuleiro tabuleiro, int decrescente, int casasLinha, int casaInicial){
        String prefix = " ".repeat(decrescente * (96 - (casasLinha * 4)));
        System.out.println(prefix + "+---".repeat(casasLinha) + "+");

        if(decrescente == 0){
            mostrarJogadoresLinha(tabuleiro, casaInicial, casasLinha, 0, 3);
            mostrarNumeracaoLinha(casaInicial, casasLinha, false);
            mostrarJogadoresLinha(tabuleiro, casaInicial, casasLinha, 3, 6);
        }else{
            System.out.print(" ".repeat(96 - (casasLinha * 4)));
            mostrarJogadoresLinhaInvertida(tabuleiro, casaInicial, casasLinha, 0, 3);
            System.out.print(" ".repeat(96 - (casasLinha * 4)));
            mostrarNumeracaoLinhaInvertida(casaInicial, casasLinha);
            System.out.print(" ".repeat(96 - (casasLinha * 4)));
            mostrarJogadoresLinhaInvertida(tabuleiro, casaInicial, casasLinha, 3, 6);
        }

        System.out.println(prefix + "+---".repeat(casasLinha) + "+");
    }

    private void mostrarJogadoresLinha(Tabuleiro tabuleiro, int casaInicial, int casasLinha, int jogadorInicio, int jogadorFim) {
        for(int i = casaInicial; i < (casaInicial + casasLinha); i++){
            System.out.print("|");
            for(int j = jogadorInicio; j < jogadorFim; j++){
                if(j >= tabuleiro.getJogadores().size()) {
                    System.out.print(" ");
                    continue;
                }
                if(tabuleiro.getCasaJogador(j) == i) System.out.print("P");
                else System.out.print(" ");
            }
        }
        System.out.println("|");
    }

    private void mostrarNumeracaoLinha(int casaInicial, int casasLinha, boolean invertida) {
        for(int i = casaInicial; i < (casaInicial + casasLinha); i++){
            if(i < 10) System.out.print("| " + i + " ");
            else if(i < 100) System.out.print("|" + (i / 10) + " " + (i % 10));
            else System.out.print("|" + i + "");
        }
        System.out.println("|");
    }

    private void mostrarJogadoresLinhaInvertida(Tabuleiro tabuleiro, int casaInicial, int casasLinha, int jogadorInicio, int jogadorFim) {
        for(int i = casasLinha; i > 0; i--){
            System.out.print("|");
            for(int j = jogadorInicio; j < jogadorFim; j++){
                if(j >= tabuleiro.getJogadores().size()) {
                    System.out.print(" ");
                    continue;
                }
                if(tabuleiro.getCasaJogador(j) == ((casaInicial - 1) + i)) System.out.print("P");
                else System.out.print(" ");
            }
        }
        System.out.println("|");
    }

    private void mostrarNumeracaoLinhaInvertida(int casaInicial, int casasLinha) {
        for(int i = casasLinha; i > 0; i--){
            System.out.print("|" + (((casaInicial - 1) + i) / 10) + " " + (((casaInicial - 1) + i) % 10) + "");
        }
        System.out.println("|");
    }

    private void mostrarCasaConexao(int x, int totalCasas, int casa){
        System.out.println(" ".repeat(92 * x) + "|   |");
        System.out.println(" ".repeat(92 * x) + "|" + (casa / 10) + " " + (casa % 10) + "|");
        System.out.println(" ".repeat(92 * x) + "|   |");
        if(totalCasas == casa) System.out.println(" ".repeat(92 * x) + "+---+");
    }

    private void mostrarTabuleiro() {
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();

        int numCasas = tabuleiro.getCasas().size();
        int casasPrimeiraLinha = Math.min(numCasas, 23) + 1;
        int casasTerceiraLinha = (numCasas > 24) ? Math.min(numCasas - 24, 24) : 0;
        int casasQuintaLinha = (numCasas > 49) ? Math.min(numCasas - 49, 24) : 0;
        int casasSetimaLinha = (numCasas > 74) ? Math.min(numCasas - 74, 24) : 0;

        // Primeira linha (sempre crescente)
        mostrarLinha(tabuleiro, 0, casasPrimeiraLinha, 0);

        if (numCasas >= 24) {
            mostrarCasaConexao(1, numCasas, 24);
        } else {
            return;
        }

        // Segunda linha (decrescente)
        if (casasTerceiraLinha > 0) {
            mostrarLinha(tabuleiro, 1, casasTerceiraLinha, 25);
        }

        if (numCasas >= 49) {
            mostrarCasaConexao(0, numCasas, 49);
        } else {
            return;
        }

        // Terceira linha (crescente)
        if (casasQuintaLinha > 0) {
            mostrarLinha(tabuleiro, 0, casasQuintaLinha, 50);
        }

        if (numCasas >= 74) {
            mostrarCasaConexao(1, numCasas, 74);
        } else {
            return;
        }

        // Quarta linha (decrescente)
        if (casasSetimaLinha > 0) {
            mostrarLinha(tabuleiro, 1, casasSetimaLinha, 75);
        }

        if (numCasas >= 99) {
            mostrarCasaConexao(0, numCasas, 99);
        } else {
            return;
        }

        // Última casa (100)
        if (numCasas == 100) {
            mostrarLinha(tabuleiro, 0, 1, 100);
        }
    }

    private void mostrarCasa(Tabuleiro tabuleiro, int novaPosicao, Jogador jogador){
        if(novaPosicao>0){
            novaPosicao = novaPosicao - 1; // Ajusta para índice de 0
        }
        String mensagem = tabuleiro.getCasas().get(novaPosicao).aplicarEfeito(jogador);
        String casaInfo = "Casa: " + novaPosicao;

        System.out.println("+-----" + "-".repeat(mensagem.length()) + "-----+");

        int espacoEsquerda = (mensagem.length() - casaInfo.length()) / 2;
        int espacoDireita = mensagem.length() - (espacoEsquerda) - casaInfo.length();
        System.out.println("|     " + " ".repeat(espacoEsquerda) + casaInfo + " ".repeat(espacoDireita) + "     |");

        System.out.println("+-----" + "-".repeat(mensagem.length()) + "-----+");
        System.out.println("|     " + mensagem + "     |");
        System.out.println("+-----" + "-".repeat(mensagem.length()) + "-----+\n");
    }

    public void pedirAcaoJogador(String modo, String corJogador){
        limparTela();
        titulo(modo + " - jogador " + corJogador);

        mostrarTabuleiro();
    }

    public void inicioDebug(Tabuleiro tabuleiro){
        limparTela();
        System.out.println("Você escolheu o modo debug.");
        System.out.println("No modo debug, você pode escolher a casa que o jogador irá andar.");
        System.out.println("Digite o número da casa (entre 0 e " + (tabuleiro.getCasas().size() - 1) + ") ou -1 para sair do modo debug.");
    }

    public void rodadaJogadorDebug(int rodada, Jogador jogador){
        titulo("vez do jogador " + jogador.getCor());

        System.out.println("R O D A D A   " + rodada + "\n");
    }
    
    public void casaDesejadaDebug(){
        System.out.print("Digite o número da casa desejada (ou -1 para sair)\n-> ");
    }

    public void rodadaJogadorNormal(int rodada, Jogador jogador){
        titulo("vez do jogador " + jogador.getCor());

        System.out.println("R O D A D A   " + rodada + "\n");
        System.out.println("PRESSIONE");
        System.out.println("1 para rolar os dados");
        System.out.println("2 para passar a rodada");
        System.out.print("-> ");
    }

    public void jogadorNaoPodeJogar(int rodada, Jogador jogador){
        titulo("vez do jogador " + jogador.getCor());

        System.out.println("R O D A D A   " + rodada + "\n");
        System.out.println("Jogador " + jogador.getCor() + " não pode jogar nessa rodada.");

        darDelay(3000);
    }

    public void mostrarTabuleiroAposRodada(Tabuleiro tabuleiro, int novaPosicao, int rodada, Jogador jogador){
        titulo("vez do jogador " + jogador.getCor());

        mostrarCasa(tabuleiro, novaPosicao, jogador);
        mostrarTabuleiro();

        System.out.print("\nDigite 0 para continuar\n-> ");
    }

    public void rodadaPassadaNormal(int rodada, Jogador jogador){
        titulo("rodada " + rodada);
        System.out.println("Jogador " + jogador.getCor() + " decidiu pular esta rodada.");
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

    public void casaInvalidaDebug(Tabuleiro tabuleiro){
        limparTela();
        System.out.println("Casa inválida! Digite um valor entre 0 e " + (tabuleiro.getCasas().size()));
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
    
    public static void casaSurpresaTela(int tipo){
        System.out.println("\nCarta sorteada:" + tipo);
    }

    public void finalJogo(int  jogadorVitorioso, int rodada, Tabuleiro tabuleiro){
        titulo("fim de jogo");

        System.out.println("PARABÉNS, JOGADOR " + tabuleiro.getJogadores().get(jogadorVitorioso).getCor() + "!! VOCÊ VENCEU!!!");

        System.out.println("\nNúmero de jogadas de cada jogador:");
        for(int i = 0; i < tabuleiro.getJogadores().size(); i++){
            System.out.println(tabuleiro.getJogadores().get(i).getCor() + ": " + tabuleiro.getJogadores().get(i).getNumJogadas() + " jogadas");
        }
    }
}
