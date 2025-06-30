package src;

public class Sortudo extends Jogador {

    public Sortudo(Cor cor, int indice){
        super(cor, indice);
    }
    @Override
    public int rolarDados(){
        while(true){
            dado1.rolar();
            dado2.rolar();  
            if(dado1.getValor() + dado2.getValor() >= 7)
                return dado1.getValor() + dado2.getValor();
        }
    }
}