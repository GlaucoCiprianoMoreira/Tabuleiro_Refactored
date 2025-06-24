import java.util.Random;

public class Sortudo extends Jogador {

    public Sortudo(String cor){
        super(cor);
    }
    public Sortudo(String cor, int casa){
        super(cor, casa);
    }
    @Override
    public int rolarDados(){
        while(true){
            Random random = new Random();
            dado1 = random.nextInt(6) + 1;
            dado2 = random.nextInt(6) + 1;
            if(dado1 + dado2 >= 7)
                return dado1 + dado2;
        }
    }
}
