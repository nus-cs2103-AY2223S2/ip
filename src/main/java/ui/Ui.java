package ui;

import java.util.Scanner;

/**
 * Ui module for handling user inputs and outputs.
 */
public class Ui {
    private static final String ERROR_HORIZONTAL = "X".repeat(40);
    private static final String HORIZONTAL = "+=".repeat(20);
    private static final String INDENT = "> ";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Indents the next line.
     */
    public void indent() {
        System.out.println(Ui.INDENT);
    }

    /**
     * Prints a horizontal line.
     */
    public void showLine() {
        System.out.println(Ui.HORIZONTAL);
    }

    /**
     * Greets the user.
     */
    public void sayHi() {
        System.out.println("Hallo Hallo niece and nephew! My name is Uncle Roger.");
        System.out.println("What you want?");
        this.showLine();
    }

    /**
     * Greets the user goodbye.
     */
    public void sayBye() {
        System.out.println("Bye Bye. Leave good review please! PLEAASEEE!");
        this.showLine();
    }

    /**
     * Reads the next line of Command from the user.
     * @return The string command of the user.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Prints the errorMessage.
     * @param errorMessage Message to be printed.
     */
    public void showError(String errorMessage) {
        System.out.println(Ui.ERROR_HORIZONTAL);
        System.out.println(errorMessage);
        System.out.println(Ui.ERROR_HORIZONTAL);
    }
}
