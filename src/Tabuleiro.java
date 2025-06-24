package src;

import java.util.ArrayList;
import java.util.Random;

public class Tabuleiro{
    public static Tabuleiro tabuleiro;
    private ArrayList<Jogador> jogadores;
    private ArrayList<Integer> casas_jogadores;

    private Tabuleiro(){
        this.jogadores = new ArrayList<>();
        this.casas_jogadores = new ArrayList<>();
    }
    public static Tabuleiro getInstancia(){
        if(tabuleiro == null)
            tabuleiro = new Tabuleiro();
        return tabuleiro;
    }

    public ArrayList<Jogador> getJogadores(){
        return jogadores;
    }

    public void setCasaJogador(int player, int novaCasa){
        casas_jogadores.set(player, novaCasa);
    }

    public Integer getCasaJogador(int player){
        return casas_jogadores.get(player);
    }
}