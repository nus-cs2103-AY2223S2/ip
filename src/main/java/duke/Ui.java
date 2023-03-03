package duke;

import java.util.Scanner;

/**
 * Encapsulates the interaction between user and program.
 */
public class Ui {
    /**
     * Reads the command entered by the user.
     *
     * @return the input to be parsed.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String showError(Exception e) {
        return e.getMessage();
    }
}
