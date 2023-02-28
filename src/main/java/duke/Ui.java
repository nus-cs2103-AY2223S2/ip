package duke;
import java.util.Scanner;

import duke.tasks.Task;
/**
 * It creates a new instance of the Ui class.
 */
public class Ui {
    public Ui() {
    }

    /**
     * It returns a string that contains a logo and a welcome message
     * @return A string
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello i'm\n" + logo + "What can i do for you?";
    }

    public void showLine() {
        System.out.println("____________________________________________");
    }

    /**
     * It reads a line of text from the console and returns it as a string
     * @return The next line of input from the user.
     */
    public String readCommand() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public String showLoadingError() {
        return "Unable to load tasks from storage";
    }

    /**
     * This function takes in a task and the number of tasks in the list, and returns a string that
     * prints out the task and the number of tasks in the list
     * @param t the task that was added
     * @param numberOfTasks the number of tasks in the list
     */
    public String printAddedTask(Task t, int numberOfTasks) {
        return "Got it. I've added this task:\n" + t.toString()
            + "\n" + "Now you have " + numberOfTasks + " tasks in the list.\n";
    }

    public String printMarked(Task t) {
        return "Nice! I've marked this task as done:\n" + t.toString();
    }

    public String printUnmarked(Task t) {
        return "OK, I've marked this task as not done yet:\n" + t.toString();
    }

    public String printExitMessage() {
        return "Bye. Hope to see you again!";
    }
    /**
     * This function returns a string that contains the task that was removed, the number of tasks left
     * in the list, and a message that says "Noted. I've removed this task"
     * @param t the task that was removed
     * @param numberOfTasks the number of tasks in the list after the removal
     */
    public String printRemovedMessage(Task t, int numberOfTasks) {
        return "Noted. I've removed this task:\n"
                + t.toString() + "\n" + "Now you have " + numberOfTasks + " tasks in the list.\n";
    }
    public String printFindTask() {
        return "Here are the matching tasks in your list:\n";
    }

    public String showError(String error) {
        return error;
    }
    public String printErrorMessage() {
        return "oops, something went wrong :(";
    }
    public String printInvalidCommandError() {
        return "OOPS!!! I'm sorry, but I don't know what that means :(((((";
    }
    public String printInvalidIndex() {
        return "Sorry, you used an invalid index";
    }
    public String printNoTasksError() {
        return "Sorry, you have no tasks yet";
    }
}
