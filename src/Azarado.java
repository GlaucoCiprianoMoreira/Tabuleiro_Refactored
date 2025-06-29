package src;

public class Azarado extends Jogador {

    public Azarado(String cor, int indice){
        super(cor, indice);
    }
    @Override
    public int rolarDados() {
        int valor1, valor2, soma;
        do {
            dado1.rolar();
            dado2.rolar();
            valor1 = dado1.getValor();
            valor2 = dado2.getValor();
            soma = valor1 + valor2;
        } while (soma > 6);
        return soma;
    }
}
