package src;
public class FactoryCasa {
    public Casa fazerCasa(int escolha) {
        switch (escolha) {
            case 1:
                return new CasaSurpresa();
                break;
            case 2:
                return new CasaNormal();
            case 3:
                return new CasaAzar();
            case 4:
                return new CasaSorte();
            case 5:
                return new CasaReversa();
            case 6:
                return new CasaPrisao();
            default:
                throw new IllegalArgumentException("Escolha inválida: " + escolha);
        }

    }
}
