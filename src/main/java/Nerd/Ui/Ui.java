package Nerd.Ui;

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
        System.out.println("Hello! I'm NerdBot\nWhat can I do for you?");
        printDivider();
    }

    public void showCommandList() {
        String availableCommands = "Available commands:\n"
                + "todo [description]\n"
                + "event [description] /from [date] /to [date]\n"
                + "deadline [description] /by [date]\n"
                + "mark [index]\n"
                + "unmark [index]\n"
                + "list\n"
                + "delete [index]\n"
                + "date [date]";
        System.out.println(availableCommands);
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

    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------");
    }

    public void print(String input) {
        System.out.println(input);
    }

}
