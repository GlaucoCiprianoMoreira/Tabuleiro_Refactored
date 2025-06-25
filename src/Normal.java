package src;

public class Normal extends Jogador {

    public Normal(String cor, int indice){
        super(cor, indice);
    }

    public int rolarDados(){
        dado1.rolar();
        dado2.rolar();
        return dado1.getValor() + dado2.getValor();
    }

}
