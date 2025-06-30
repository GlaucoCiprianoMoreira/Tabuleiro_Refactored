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
            case "verde":
                return "\u001B[92m";
            case "ciano": 
                return "\u001B[38;5;51m";
            case "roxo":
                return "\u001B[95m";
            case "vermelho":
                return "\u001B[38;5;196m";
            case "rosa":
                return "\u001B[38;5;198m";
            case "amarelo":
                return "\u001B[93m";
            default:
                throw new IllegalArgumentException("Nome inválido");
            }
	}
	public static String resetCor(){
        return "\u001B[0m";
    }
}