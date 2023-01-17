package smartduke;

import java.util.Scanner;

public class Ui {
    Scanner userInput;

    public Ui() {
        this.userInput = new Scanner(System.in);
    }

    public void showWelcome() {
        String greeting = "Yo i'm SmartDuke.\n" +
                "     how can i help you?";
        System.out.println(greeting);
    }

    public void showSuccess(String succMsg) {
        System.out.println(succMsg);
    }

    public void showError(String errMsg) {
        System.out.println(errMsg);
    }

    public void showLine() {
        System.out.println("--------------------------");
    }

    public String readCommand() {
        String userCommand = this.userInput.nextLine();
        return userCommand;
    }

    public void close() {
        this.userInput.close();
    }
}
