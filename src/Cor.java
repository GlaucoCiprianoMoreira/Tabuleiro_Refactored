package src;

public class Cor {

	private String nome;
	
	public Cor(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String codigoCor() {
		switch(nome) {
            case "azul":
                return "\u001B[34m";
            case "verde": 
                return "\u001B[32m";
            case "amarelo":
                return "\u001B[33m";
            case "laranja":
                return "\u001B[33m";
            case "vermelho":
                return "\u001B[31m";
            case "rosa":
                return "\u001B[95m";
            default:
                throw new IllegalArgumentException("Nome inválido");
            }
	}
	public static String resetCor(){
        return "\u001B[0m";
    }
}