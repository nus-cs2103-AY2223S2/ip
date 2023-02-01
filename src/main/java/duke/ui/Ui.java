package duke.ui;

import java.util.Scanner;

/**
 * User interface class to display outputs and inputs.
 */
public class Ui {

    /**
     * Shows the welcome message upon the running of the software.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I am Duke the Chatbot!\nHow may i help you today?\n");
    }

    /**
     * Shows the dotted line.
     */
    public void showLine() {
        System.out.println("----------------------------------------------------");
    }

    /**
     * Reads the next line which is the command.
     *
     * @return String Command that is inputted.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Shows the error which causes the data to be unable to be loaded.
     *
     * @param e Error message to be outputed.
     */
    public void showLoadingError(String e) {
        System.out.println("Cannot load data from storage due to: " + e);
    }

}
