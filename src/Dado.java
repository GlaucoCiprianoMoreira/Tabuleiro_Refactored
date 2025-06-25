package src;
import java.util.Random;
public class Dado {
    private Random random;
    private int valor;

    public Dado() {
        random = new Random();
    }

    public void rolar() {
        valor = random.nextInt(6) + 1;
    }

    public int getValor() {
        return valor;
    }
}