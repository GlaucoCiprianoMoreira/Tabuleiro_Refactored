package src;

public class JogadorFactory {
    private JogadorFactory() {
        // Construtor privado para evitar instância
    }
    
    public static Jogador criarJogador(Cor cor, int indice, int escolha) {
        switch (escolha) {
            case 1:
                return new Sortudo(cor, indice);
            case 2:
                return new Normal(cor, indice);
            case 3:
                return new Azarado(cor, indice);  
        }
        return null; // Retorna null se a escolha for inválida
    }
            
}
