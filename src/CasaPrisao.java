package src;
public class CasaPrisao extends Casa{
    @Override
    public String aplicarEfeito(Jogador jogador){
        if (!jogador.getPodeJogar()){ 
            jogador.setPodeJogar(true);
            return "Agora você pode jogar";
        }else{
            jogador.setPodeJogar(false);

        }
        return "Você foi preso! Não pode jogar na próxima rodada.";
        
    }
}
