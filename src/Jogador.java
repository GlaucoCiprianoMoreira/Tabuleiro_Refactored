package src;

public abstract class Jogador{
    private Cor cor;
    protected int casaAtual;
    protected int dado1, dado2;
    protected boolean podeJogar;
    protected int numJogadas;
    
    public Jogador(int num_cor){
        this.cor = CorFactory.instanciarCor(num_cor);
        this.casaAtual = 0;
        this.podeJogar = true;
    }
    public Jogador(int num_cor, int casa){
        this.cor = CorFactory.instanciarCor(num_cor);
        this.casaAtual = casa;
        this.podeJogar = true;
    }
    public abstract int rolarDados();

    public String getCor(){
        return cor.getNome();
    }
    public int getCasaAtual(){
        return casaAtual;
    }
    public void setCasaAtual(int novaCasa){
        this.casaAtual = novaCasa;
    }
    public void somaCasaAtual(int novaCasa){
        this.casaAtual += novaCasa;
    }
    public int getDado1(){
        return dado1;
    }
    public int getDado2(){
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