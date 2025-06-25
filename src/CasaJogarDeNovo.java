package src;
public class CasaJogarDeNovo extends Casa {
    
    @Override
    public void aplicarEfeito(Jogador jogador ) {
        System.out.println("Você caiu na casa de jogar novamente! Role os dados novamente.");
        jogador.rolarDadods();
    }    
}
