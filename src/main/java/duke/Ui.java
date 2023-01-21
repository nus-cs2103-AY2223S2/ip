package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Ui class handles all logic involved with the duke app communicating
 * with the user.
 */
public class Ui {

    private BufferedReader reader;

    public Ui() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Method that reads the user's text input.
     *
     * @return User's input as a string.
     */
    public String input() throws IOException {
        return this.reader.readLine();
    }

    /**
     * Prints welcome statement upon starting the duke app
     *
     */
    public void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        line();
        indent("Hi, I'm Duke ");
        indent("What can I do for you :) ?\n");
        line();
    }

    /**
     * Prints ending message when user terminates the duke app
     *
     */
    public void end() {
        line();
        indent("Bye. Hope to hear from you again! :)");
        line();
    }

    /**
     * Prints error message for when user provides empty dates.
     */
    public static void emptyError() {
        indent("Sorry! You provided an empty description. Pls provide a correct input :)");
        line();
    }

    /**
     * Error message for when the user input an invalid statement
     */
    public static void invalidInputError() {
        indent("Sorry! I did not quite understand what you meant :( Pls try again!");
        line();
    }

    /**
     * Message for un-mark operation
     */
    public static void printUnmark(TaskList taskList, int i) {
        line();
        indent("Alright! I've unmarked this task :(\n");
        indent("  " + taskList.get(i - 1));
        line();
    }

    /**
     * Message for mark operation
     */
    public static void printMark(TaskList taskList, int i) {
        line();
        indent("Alright! I've marked this task as done :) !\n");
        indent("  " + taskList.get(i - 1));
        line();
    }

    /**
     * Message for delete operation
     */
    public static void printDelete(TaskList taskList, int i, int size) {
        line();
        indent("OK! I've deleted this task :)\n");
        indent("  " + taskList.get(i - 1));
        indent(String.format("Now you have %d tasks in the list", size - 1));
        line();
    }

    /**
     * Message for list operation
     */
    public static void printListCommand(TaskList taskList) {
        line();
        indent("Here are the remaining tasks in your list:\n");
        taskList.printList();
        line();
    }

    /**
     * Message for add task operation
     */
    public static void printAddTask(Task task, int size) {
        line();
        indent("Roger! I've added this task to the list:\n");
        indent(task + "\n");
        indent(String.format("Now you have %d tasks left in the list", size));
        line();
    }


    /**
     * function to indent duke system messages
     */
    public static void indent(String txt) {
        System.out.println("     " + txt);
    }

    public static void printFindList(TaskList taskList, String input) {
        line();
        indent("Here are the matching tasks in your list");
        taskList.findList(input);
        line();
    }

    public static void line() {
        System.out.println("____________________________________________________________________________________");
    }
}
