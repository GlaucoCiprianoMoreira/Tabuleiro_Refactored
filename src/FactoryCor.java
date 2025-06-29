package src;

public class FactoryCor {
	
    private FactoryCor() {
        // Construtor privado para evitar instância
    }

	public static Cor instanciarCor(int id) {
		switch(id) {
		  case 1:
              return new Cor("azul");
          case 2:
              return new Cor("verde");
          case 3:
              return new Cor("amarelo");
          case 4:
              return new Cor("laranja");
          case 5:
              return new Cor("vermelho");
          case 6:
              return new Cor("rosa");
          default:
              throw new IllegalArgumentException("ID de cor inválido: " + id);
		}
	}
	
}