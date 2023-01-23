package twofive.ui;

import java.util.Scanner;

public class Ui {
    private static final String LOGO = "  _______            ______ _\n"
            + " |__   __|          |  ____(_)\n"
            + "    | |_      _____ | |__   ___   _____\n"
            + "    | \\ \\ /\\ / / _ \\|  __| | \\ \\ / / _ \\\n"
            + "    | |\\ V  V / (_) | |    | |\\ V /  __/\n"
            + "    |_| \\_/\\_/ \\___/|_|    |_| \\_/ \\___|\n";
    private static final String DIVIDER = "____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        this.showLine();
        System.out.println("In case you're still not clear, I'm TwoFive!");
        System.out.println("I'm your personal assistant chatbot");
        System.out.println("What can I do for you?");
        this.showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showExit() {
        System.out.println(" Tasks saved successfully.");
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public void showMesssage(String message) {
        System.out.println(message);
    }
}
