package duke;

import java.util.Scanner;

/**
 * Ui represents the user interface, which in this case is the console.
 */
public class Ui {
    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public void println(String string) {
        System.out.println("\t" + string + "\n");
    }

    public void println(Object object) {
        System.out.println("\t" + object.toString() + "\n");
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
        System.out.println("\t---");
        System.out.println("\t" + string.replace("\n", "\n\t").replaceAll("\\t$", ""));
        System.out.println("\t---");
    }

    /**
     * Prints the goodbye message.
     */
    public void printEnd() {
        System.out.println("\t---");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t---");
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public boolean hasNextCommand() {
        return this.scanner.hasNextLine();
    }
}
