package Ui;

import java.util.Scanner;

public class Ui {

    private final static String BANNER = "____________________________________________________________";
    private final static String WELCOME_MSG = "Greetings! JEDI GRANDMASTER YODA here\n" + "For you, What can I do?";
    private final static String BYE_MSG = "Be Gone, You Must. May the Force be with You!";

    /**
     * Responds to the command given by standard input with the appropriate formatting.
     * @param command the input that is retrieved from standard input
     * @param nextLine provides necessary indent should requested
     */
    public void respond(String command, boolean nextLine) {
        String answer = "";
        if (nextLine) {
            answer += BANNER;
        }
        answer += "\n" + command + "\n" + BANNER;
        System.out.println(answer);
    }

    public void displayByeMessage() {
        System.out.println(BYE_MSG);
    }

    public String readCommand(Scanner scanner) {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message when launched.
     */
    public void displayWelcomeMessage() {
        System.out.println(String.format("%s\n%s\n%s", BANNER, WELCOME_MSG, BANNER));
    }

    public void showLoadingErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
