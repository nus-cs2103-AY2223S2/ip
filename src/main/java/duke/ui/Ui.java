package duke.ui;

import java.util.Scanner;

/**
 * Ui represents the user interface, which in this case is the console.
 */
public class Ui {
    private static final String DELIMITER = "\t---";
    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    /**
     * Prints the start message.
     */
    public void printStart() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
    }

    /**
     * Add a tab to every line.
     * @param string String to print.
     */
    public void printBlock(String string) {
        System.out.println(DELIMITER);
        System.out.println("\t" + string.replace("\n", "\n\t").replaceAll("\\t$", ""));
        System.out.println(DELIMITER);
    }

    /**
     * Prints the goodbye message.
     */
    public void printEnd() {
        System.out.println(DELIMITER);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(DELIMITER);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public boolean hasNextCommand() {
        return this.scanner.hasNextLine();
    }
}
