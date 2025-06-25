package src;

public abstract class Jogador{
    protected int indice;
    protected String cor;
    protected Dado dado1, dado2;
    protected boolean podeJogar;
    protected int numJogadas;
    
    public Jogador(String cor, int indice){
        this.cor = cor;
        this.indice = indice;
        this.podeJogar = true;
    }
    public abstract int rolarDados();

    public String getCor(){
        return cor;
    }
    public int getIndice(){
        return indice;
    }
    public Dado getDado1(){
        return dado1;
    }
    public Dado getDado2(){
        return dado2;
    }
    public void setPodeJogar(boolean pode){
        this.podeJogar = pode;
    }
    public boolean getPodeJogar(){
        return podeJogar;
    }
    public int getNumJogadas(){
    	return numJogadas;
    }
    public void setNumJogadas(int num) {
    	this.numJogadas = num;
    }
}