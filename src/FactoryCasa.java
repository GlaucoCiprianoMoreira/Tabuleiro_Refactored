package src;
public class FactoryCasa {
    private FactoryCasa() {
        // Construtor privado para evitar instância
    }
    
    public static Casa fazerCasa(int escolha) {
        switch (escolha) {
            case 0:
                return new CasaNormal();
            case 1:
                return new CasaSurpresa();
            case 2:
                return new CasaAzar();
            case 3:
                return new CasaSorte();
            case 4:
                return new CasaReversa();
            case 5:
                return new CasaPrisao();
            case 6:
                return new CasaJogarDeNovo();
            default:
                throw new IllegalArgumentException("Escolha inválida: " + escolha);
        }

    }
}
