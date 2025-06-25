package src;
public class CasaPrisao extends Casa{
    public void aplicarEfeito(Jogador jogador){
        if (jogador.getPodeJogar() == false){ 
            jogador.setPodeJogar(true);
        }else{
            jogador.setPodeJogar(false);
        }
        return null;
    }
}
