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
     * @return
     */
    public static String welcomeMessage() {
        return "Eren Jaeger, it is me, Captain Levi. How may I be of assistance?";
    }

    public static String title() {
        return "AOT chatbot";
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
        System.out.println("Wrong format for date! [Format: dd/MM/yyyy]");
    }

    /**
     * Prints a message every time a new task has been added.
     */
    public static String addTaskMessage() {
        return "I've added this task for you!";
    }

    /**
     * Prints an exit message when the program ends.
     * @return
     */
    public static String exitMessage() {
        return "See ya champ! Enjoy your day!";
    }

    /**
     * Prints the list of tasks in the arrayList.
     */
    public static String listMessage() {
        return "Here are the task(s) in your list:";
    }

    /**
     * Prints a message every time a task is deleted.
     */
    public static String deleteMessage() {
        return "I've deleted this task for you!";
    }

    public static String findMessage(String text) {
        return "Task(s) that starts with " + "\"" + text + "\"" + ":";
    }

    public static String erenMessage(String text) {
        return "Eren says: " + text;
    }

    public static String wrongInputMessage() { return "Eren, I do not understand your request."; }
}
