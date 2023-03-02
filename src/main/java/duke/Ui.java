package duke;

import java.util.Scanner;

/**
 * The Ui class that is used to interact with the user for input and output
 */
public class Ui {
    private static final String LINE = "~~~~~~~~~~~~~~~~~~~~";
    private final Scanner sc;

    /**
     * Returns a Ui object with the input as the command line
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints error if there is an issue with loading
     */
    public void showLoadingError() {
        // error in reading the file
        print("error");
    }

    /**
     * Prints the String given
     *
     * @param toBePrinted String
     */
    public void print(String toBePrinted) {
        System.out.println(toBePrinted);
    }

    /**
     * Prints the error message given in the format for errors
     *
     * @param errorMessage String
     */
    public void showError(String errorMessage) {
        print(errorMsg(errorMessage));
    }

    /**
     * Returns a String that formats the error message given
     *
     * @param error String
     * @return String formatted error message
     */
    public String errorMsg(String error) {
        return String.format("☹ OOPS!!!\n%s :-(", error);
    }
    /**
     * Echoes the input, printing the command that was input
     *
     * @param command String
     */
    public void echo(String command) {
        System.out.println(command);
    }

    /**
     * Returns a String of the name DUKE formatted
     *
     * @return name String
     */
    private String ownName() {
        String name = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return name;
    }

    /**
     * Prints the greeting message for Duke
     */
    public void greet() {
        print(String.format("Hello I am:\n%sWhat can I do for you?", ownName()));
    }

    /**
     * Prints a line used to separate the inputs and outputs
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Reads the command in the line and returns it as a String
     *
     * @return String
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Closes the scanner as it is good practice
     */
    public void closeUi() {
        this.sc.close();
    }
}
