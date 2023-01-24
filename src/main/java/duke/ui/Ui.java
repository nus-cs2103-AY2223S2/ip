package duke.ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);
    private static String formatMessage(String message) {
        String FORMAT_LINE = "___________________________";
        return FORMAT_LINE + "\n"
                + message + "\n"
                + FORMAT_LINE;
    }

    public void printMessage(String message) {
        System.out.println(formatMessage(message));
    }

    public String getInputFromUser() {
        return scanner.nextLine();
    }

    public void printPromptForInput() {
        System.out.print(">");
    }

    public void greet() {
        printMessage("Hello, I am Duke.\n"
                + "What can I do for you?");
    }

    public void sayGoodbye() {
        printMessage("Goodbye. I hope to see you again.");
    }
}
