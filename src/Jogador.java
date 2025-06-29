package src;

public abstract class Jogador{
    protected int indice;
    protected Cor cor;
    protected Dado dado1;
    protected Dado dado2;
    protected boolean podeJogar;
    protected int numJogadas;
    
    protected Jogador(Cor cor, int indice){
        this.cor = cor;
        this.indice = indice;
        this.podeJogar = true;
        this.dado1 = new Dado();
        this.dado2 = new Dado();
    }
    public abstract int rolarDados();

    public Cor getCor(){
        return cor;
    }
    public String getCodigoCor(){
        return cor.toString();
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