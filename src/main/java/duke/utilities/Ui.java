package duke.utilities;

import java.util.Scanner;

public class Ui {

    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        String welcomeMessage = "============================================================\n"
                + "Welcome to Duchess\n"
                + "============================================================\n";
        System.out.print(welcomeMessage);
    }

    public void showPrompt() {
        System.out.print("\n>> ");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showNumberOfTasks(int numTasks) {
        if (numTasks == 1) {
            System.out.println("There is 1 task in the list");
        } else {
            System.out.println("There are " + numTasks + " tasks in the list");
        }
    }

    public String readLine() {
        return sc.nextLine();
    }
}
