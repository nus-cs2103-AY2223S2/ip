package munch;

import java.util.Scanner;

public class Ui {

    /**
     * Constructor for Ui object.
     */
    public Ui() {}

    /**
     * Scans the command input from the user.
     * @return String representation of the input.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints a welcome message when the program is first being started.
     */
    public static void welcomeMessage() {
        System.out.println("Hello! I am Munch! :)");
        System.out.println("How may I help you?");
        System.out.println("__________________________________");
    }

    /**
     * Prints a divider line to improve user interface.
     */
    public static void divider() {
        System.out.println("__________________________________");
    }

    /**
     * Prints an error message when the input format for date is wrong.
     */
    public static void wrongDateFormatMessage() {
        System.out.println("Wrong format for date! [Format: dd/MM/yyyy HHmm]");
    }

    /**
     * Prints a message every time a new task has been added.
     */
    public static void addTaskMessage() {
        System.out.println("I've added this task for you!");
    }

    /**
     * Prints an exit message when the program ends.
     */
    public static void exitMessage() {
        System.out.println("See ya champ! Enjoy your day!");
    }

    /**
     * Prints the list of tasks in the arrayList.
     */
    public static void listMessage() {
        System.out.println("Here are the task(s) in your list:");
    }

    /**
     * Prints a message every time a task is deleted.
     */
    public static void deleteMessage() {
        System.out.println("I've deleted this task for you!");
    }
}
