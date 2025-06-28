package src;

public class Cor {

	private String nome;
	
	public Cor(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this + this.nome.toUpperCase() ;
	}
	
	public String toString() {
		switch(nome) {
            case "azul":
                return "\u001B[34m";
            case "vermelho": 
                return "\u001B[31m";
            case "rosa":
                return "\u001B[38;5;205m";
            case "amarelo":
                return "\u001B[33m";
            case "verde":
                return "\u001B[32m";
            case "roxo":
                return "\u001B[35m";
            default:
                throw new IllegalArgumentException("Nome inválido");
            }
	}
	public String resetCor(){
        return "\u001B[0m";
    }
}