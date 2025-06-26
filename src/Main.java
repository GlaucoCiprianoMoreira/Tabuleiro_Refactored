package src;

public class Main {

    public static void main(String[] args){
        JogoFacade jogo = new JogoFacade();
        int numJogadores = jogo.getNumJogadores();
        int numCasas = jogo.getNumCasas();
        jogo.configTabuleiro(numCasas);
        jogo.config(numJogadores);
        jogo.startJogo();        
    }
    
}