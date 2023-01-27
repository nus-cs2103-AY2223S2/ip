package red.ui;

import java.util.Scanner;

public class UI {

    private static Scanner scanner;

    public void sayHello() {
        System.out.println("Red is ready to assist you\n");
    }

    public String readCommand() {
        scanner = new Scanner(System.in);
        return scanner.nextLine();

    }

    public void showLine() {
        System.out.println("********************************************");
    }

    public void showError(String err) {
        System.out.println("Error Message String = " + err);


    }
}