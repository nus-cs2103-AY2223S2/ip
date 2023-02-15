package elems;

import java.util.Scanner;

/**
 * Represents the User Interface that takes in input and displays output to the user
 * @author clydelhui
 */
public class Ui {

    private static final String logo = " ____        _        \n"
                              + "|  _ \\ _   _| | _____ \n"
                              + "| | | | | | | |/ / _ \\\n"
                              + "| |_| | |_| |   <  __/\n"
                              + "|____/ \\__,_|_|\\_\\___|\n";

    private final Scanner userInput;

    public Ui(Scanner userInput) {
        this.userInput = userInput;
    }

    public void display(String displayText) {
        System.out.println(displayText);
    }

    public String getInput() {
        String input = this.userInput.nextLine();
        return input;
    }

    public void welcome() {
        System.out.println("Hello from\n" + Ui.logo);
    }

    public void errorDisplay(Exception exception) {
        System.err.println(exception.getMessage());
    }

}
