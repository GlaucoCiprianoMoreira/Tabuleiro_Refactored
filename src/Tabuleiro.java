package src;

import java.util.ArrayList;

public class Tabuleiro {
    private static Tabuleiro instancia; // instância única (singleton)

    private ArrayList<Jogador> jogadores;
    private ArrayList<Casa> casas;
    private int[] casaJogador;

    // Construtor privado
    private Tabuleiro() {
        this.jogadores = new ArrayList<>();
        this.casas = new ArrayList<>();
    }

    // Método de acesso público
    public static Tabuleiro getInstancia() {
        if (instancia == null) {
            instancia = new Tabuleiro();
        }
        return instancia;
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public void adicionarJogadores(Jogador j) {
        jogadores.add(j);
    }

    public ArrayList<Casa> getCasas() {
        return casas;
    }
    public void criarCasaJogador(){
        casaJogador = new int[jogadores.size()];
    }
    public void setCasaJogador(int indice, int casa){
        casaJogador[indice] = casa;
    }
    public int getCasaJogador(int indice){
        return casaJogador[indice];
    }
    public void adicionarCasa(Casa casa) {
        casas.add(casa);
    }
}