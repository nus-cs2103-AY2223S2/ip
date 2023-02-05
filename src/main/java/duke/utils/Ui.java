package duke.utils;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.taskers.Task;

/**
 * UI class the handles the user interaction.
 */
public class Ui {

    private Scanner in;
    private PrintStream out;

    /**
     * Constructor.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructor.
     *
     * @param in Input.
     * @param out Output.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prints out the matching tasks.
     *
     * @param arrList Array of matching tasks.
     */
    public String showMatchingTasks(ArrayList<Task> arrList) {
        String totalString = "";
        totalString += "     Here are the matching tasks in your list:";
        for (int i = 0; i < arrList.size(); i++) {
            totalString += "\n     " + (i + 1) + "." + arrList.get(i).toString();
        }
        return totalString;
    }


    /**
     * Shows the list in the task list.
     *
     * @param arrList The array of tasks to be shown.
     */
    public String showList(ArrayList<Task> arrList) {
        String totalString = "";
        totalString += "     Here are the tasks in your list:";
        for (int j = 0; j < arrList.size(); j++) {
            totalString += "\n     " + (j + 1) + "." + arrList.get(j).toString();
        }

        return totalString;
    }

    /**
     * Duke greets when the program first starts.
     */
    public String greet() {
        return "     Hello! I am Duke!\n     What can I do for you today?";
    }

    /**
     * Response when task is marked.
     *
     * @param t duke.taskers.Task to be marked.
     */
    public String markResponse(Task t) {
        String str = "     Nice! I've marked this task as done!:\n      " + t.toString();
        return str;
    }

    /**
     * Response when task is unmarked.
     *
     * @param t duke.taskers.Task to be unmarked.
     */
    public String unmarkResponse(Task t) {
        String str = "     OK, I've marked this task as not done yet:\n      "
                + t.toString();
        return str;
    }

    /**
     * Response when item is added.
     *
     * @param t duke.Task to be added.
     * @param arr Array for the task to be added to.
     */
    public String addItemResponse(Task t, ArrayList<Task> arr) {
        String str = "  " + t.toString();
        str = "     Got it. I've added this task:\n    " + str;
        return str + listUpdate(arr);

    }

    /**
     * Replies after prioritising.
     *
     * @param t The task to be prioritised
     * @return The string response
     */
    public String prioritiseResponse(Task t) {
        String str = "  " + t.toString();
        str = "     Got it. I've prioritised this task:\n    " + str;
        return str;
    }

    /**
     * Replies after unprioritising.
     *
     * @param t The task to be unprioritised
     * @return The string response
     */
    public String unprioritiseResponse(Task t) {
        String str = "  " + t.toString();
        str = "     Got it. I've unprioritised this task:\n    " + str;
        return str;
    }

    /**
     * Printing out the special error when the Duke Exception is thrown.
     *
     * @param e The duke exception that is being thrown.
     */
    public String showError(DukeException e) {
        return "     Duke Exception: OOPS! " + e.getMessage();
    }

    /**
     * Replies when the item is deleted.
     *
     * @param t The task to be deleted.
     * @param arr The array from the task to be deleted.
     */
    public String deleteItemResponse(Task t, ArrayList<Task> arr) {
        String str = "     Noted. I'm removing this task:\n      " + t.toString();
        return str + listUpdate(arr);

    }

    /**
     * Says goodbye when the program ends.
     */
    public void sayGoodBye() {
        this.out.println("     Bye! Hope to see you again soon!");
    }

    /**
     * Updates the list.
     *
     * @param arr The array that is remaining after something is done.
     * @return The string reply.
     */
    public String listUpdate(ArrayList<Task> arr) {
        String plural = "";
        if (arr.size() > 1) {
            plural = "s";
        }
        return "\n     Now you have " + arr.size()
                + " task" + plural + " in the list.";
    }

}
