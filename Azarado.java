import java.util.Random;

public class Azarado extends Jogador {

    public Azarado(String cor){
        super(cor);
    }
    public Azarado(String cor, int casa){
        super(cor, casa);
    }
    public int rolarDados(){
        while(true){
            Random random = new Random();
            dado1 = random.nextInt(6) + 1;
            dado2 = random.nextInt(6) + 1;
            if(dado1 + dado2 <= 6)
                return dado1 + dado2;
        }
    }
}
