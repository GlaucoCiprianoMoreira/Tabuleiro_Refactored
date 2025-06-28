package src;

import java.util.Scanner;

public class Entrada {
    private static final Scanner scanner = new Scanner(System.in);

    private Entrada() {
        // Construtor privado para evitar instância
    }

    public static Scanner getScanner(){
        return scanner;
    }
}
