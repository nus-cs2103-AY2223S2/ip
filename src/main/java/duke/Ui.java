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
    public static String start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String output = "Hello from\n" + logo;

        output = output + "Hi, I'm Duke ";
        output = output + "What can I do for you :) ?\n";
        return output;
    }

    /**
     * Prints ending message when user terminates the duke app
     *
     */
    public String end() {
        return "Bye. Hope to hear from you again! :)";
    }

    /**
     * Prints error message for when user provides empty dates.
     */
    public static String emptyError() {
        return "Sorry! You provided an empty description. Pls provide a correct input :)";
    }

    /**
     * Error message for when the user input an invalid statement
     */
    public static String invalidInputError() {
        return "Sorry! I did not quite understand what you meant :( Pls try again!";
    }

    /**
     * Message for un-mark operation
     */
    public static String printUnmark(TaskList taskList, int i) {
        return "Alright! I've unmarked this task :(\n" + taskList.get(i - 1);
    }

    /**
     * Message for mark operation
     */
    public static String printMark(TaskList taskList, int i) {
        return "Alright! I've marked this task as done :) !\n" + taskList.get(i - 1);
    }

    /**
     * Message for delete operation
     */
    public static String printDelete(Task task, int i, int size) {
        String s = "OK! I've deleted this task :)\n";
        s = s + task;
        s = s + String.format("Now you have %d tasks in the list", size - 1);
        return s;
    }

    /**
     * Message for list operation
     */
    public static String printListCommand(TaskList taskList) {
        return "Here are the remaining tasks in your list:\n" + taskList.printList();
    }

    /**
     * Message for add task operation
     */
    public static String printAddTask(Task task, int size) {
        String s = "Roger! I've added this task to the list:\n";
        s = s + task + "\n";
        s = s + String.format("Now you have %d tasks left in the list\n", size);
        return s;
    }



    public static String printFindList(TaskList taskList, String input) {
        String s = "Here are the matching tasks in your list";
        return s + taskList.findList(input) + "\n";
    }

    public static String printMarkPriority(TaskList taskList, int index, int p) {
        String s = "This task has now been marked as ";
        String prio = Task.arr[p] + " priority! \n";
        return s + prio + taskList.get(index - 1);
    }

}
