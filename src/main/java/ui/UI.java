package ui;

import java.util.Scanner;

/**
 * UI is a program that helps read user input and print some outputs
 * @author CShuwen
 * @version 1.0
 * @since 0.0
 */
public class UI {
    private final Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public String showWelcome() {
        return "Hello! I'm Duke.Duke\n" + "What can I do for you?";
    }


    public void showLine() {
        System.out.println("_________________________________");
    }

    public void showError(String e) {
        System.out.println(e);
    }

    public String readCommand() {
        return scanner.nextLine();
    }




}
