package duke.utils;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
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
     * Reads the command on the command line.
     *
     * @return String of the command.
     */
    public String readCommand() {
        return this.in.nextLine();
    }

    /**
     * Prints out the matching tasks.
     *
     * @param arrList Array of matching tasks.
     */
    public void showMatchingTasks(ArrayList<Task> arrList) {
        String totalString = "";
        totalString += "     Here are the matching tasks in your list:";
        for (int i = 0; i < arrList.size(); i++) {
            totalString += "\n     " + (i + 1) + "." + arrList.get(i).toString();
        }
        this.out.println(totalString);
    }


    /**
     * Shows the list in the task list.
     *
     * @param arrList The array of tasks to be shown.
     */
    public void showList(ArrayList<Task> arrList) {
        String totalString = "";
        totalString += "     Here are the tasks in your list:";
        for (int i = 0; i < arrList.size(); i++) {
            totalString += "\n     " + (i + 1) + "." + arrList.get(i).toString();
        }
        this.out.println(totalString);
    }

    /**
     * Prints out the line that sandwiches Duke's responses.
     */
    public void showLine() {
        System.out.println("    _______________________________________"
                + "________________________________________________");
    }

    /**
     * Duke greets when the program first starts.
     */
    public void greet() {
        this.showLine();
        this.out.println("     Hello! I am Duke!\n     What can I do for you today?");
        this.showLine();
    }

    /**
     * Response when task is marked.
     *
     * @param t duke.taskers.Task to be marked.
     */
    public void markResponse(Task t) {
        String str = "     Nice! I've marked this task as done!:\n      " + t.toString();
        this.out.println(str);
    }

    /**
     * Response when task is unmarked.
     *
     * @param t duke.taskers.Task to be unmarked.
     */
    public void unmarkResponse(Task t) {
        String str = "     OK, I've marked this task as not done yet:\n      "
                + t.toString();
        this.out.println(str);
    }

    /**
     * Response when item is added.
     *
     * @param t duke.Task to be added.
     * @param arr Array for the task to be added to.
     */
    public void addItemResponse(Task t, ArrayList<Task> arr) {
        String str = "  " + t.toString();
        str = "     Got it. I've added this task:\n    " + str;
        this.out.println(str + listUpdate(arr));

    }

    /**
     * Printing out the special error when the Duke Exception is thrown.
     *
     * @param e The duke exception that is being thrown.
     */
    public void showError(DukeException e) {
        System.out.println("Duke Exception: OOPS! " + e.getMessage());
    }

    /**
     * Replies when the item is deleted.
     *
     * @param t The task to be deleted.
     * @param arr The array from the task to be deleted.
     */
    public void deleteItemResponse(Task t, ArrayList<Task> arr) {
        String str = "     Noted. I'm removing this task:\n      " + t.toString();
        this.out.println(str + listUpdate(arr));

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
