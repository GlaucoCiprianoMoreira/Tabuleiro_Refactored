package src;

public class Azarado extends Jogador {

    public Azarado(String cor, int indice){
        super(cor, indice);
    }
    @Override
    public int rolarDados(){
        while(true){
            dado1.rolar();
            dado2.rolar();
            if(dado1.getValor() + dado2.getValor() <= 6) {
                return dado1.getValor() + dado2.getValor();
            }
        }
    }
}
