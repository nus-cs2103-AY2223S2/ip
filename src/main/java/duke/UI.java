package duke;

import java.util.Scanner;

public class UI {
    private Scanner sc;

    public UI() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void getWelcomeMessage() {
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
    }

    public void getFarewellMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showConfirmation(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("Running Duke for the first time - Creating required data files");
    }
}
