package src;

public class Normal extends Jogador {

    public Normal(Cor cor, int indice){
        super(cor, indice);
    }

    @Override
    public int rolarDados(){
        dado1.rolar();
        dado2.rolar();
        return dado1.getValor() + dado2.getValor();
    }

}
