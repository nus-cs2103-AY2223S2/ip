package duke;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String requestUserInput() {
        return scanner.nextLine();
    }

    public static void showLoadingErrorMessage() {
        System.out.println("Couldn't load the file for some reason");
    }

    public static void showNewUserMessage() {
        System.out.println("Oh boy a new user! What's up?");
    }

    public static void showWelcomePrompt() {
        System.out.println("How might I help you today?");
    }

    public void showWelcomeMessage() {
        String projName = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Yo! The name is\n" + projName);
    }
}
