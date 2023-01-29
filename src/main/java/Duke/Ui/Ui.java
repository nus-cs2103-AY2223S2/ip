package Duke.Ui;

import java.util.Scanner;

/**
 * Represents the user interface of the Chat bot.
 */
public class Ui {
    Scanner sc;

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke.Duke\nWhat can I do for you?");
        printDivider();
    }

    public void printDivider() {
        System.out.println("---------------------------------------");
    }

    public void printError(String err) {
        System.out.println(err);
    }

    /**
     * Starts and initializes the scanner.
     */
    public void startScanner() {
        this.sc = new Scanner(System.in).useDelimiter(" ");
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public boolean hasNextLine() {
        return this.sc.hasNextLine();
    }

    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------");
    }

    public void print(String input) {
        System.out.println(input);
    }

}
