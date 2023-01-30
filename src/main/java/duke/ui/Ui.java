package duke.ui;

import java.util.Scanner;

import duke.tasklist.TaskList;
import duke.tasktypes.Task;

/**
 * Represents a User Interface (Ui) instance.
 */
public class Ui {

    /** Scanner Object for reading User input. */
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Closes the Scanner object
     */
    public void close() {
        sc.close();
    }

    /**
     * Prints divider line.
     */
    public void showLine() {
        System.out.println("--------------------------------");
    }

    /**
     * Prints Duke's greeting message.
     */
    public String greet() {
        String greeting = "What's up! XyDuke here!\nHow can I be of assistance?\n";
        return greeting;
    }

    /**
     * Prints Duke's goodbye message.
     */
    public String goodbye() {
        String goodbye = "Bye. Hope to see you again soon!\n";
        return goodbye;
    }

    /**
     * Scans Standard Input for next User input.
     *
     * @return String representation of User input.
     */
    public String nextInput() {
        return sc.nextLine();
    }

    /**
     * Prints error message from exception instance.
     *
     * @param e Exception object whose message is to be printed.
     */
    public String showError(Exception e) {
        return e.getMessage();
    }

    /**
     * Prints directory created message.
     */
    public String directoryCreate() {
        return "Data folder created!\n";
    }

    /**
     * Prints file created message.
     */
    public String fileCreate() {
        return "Duke data file: duke.txt created!\n";
    }

    /**
     * Prints data is being saved message.
     */
    public String uploading() {
        return "Updating your data. Please wait..\n";
    }

    /**
     * Prints data has been saved message.
     */
    public String saved() {
        return "All changes saved successfully!\n";
    }

    /**
     * Prints task has been added message.
     *
     * @param task Task that was added.
     * @param numTasks Current number of tasks in Task collection. (After addition)
     */
    public String taskAdd(Task task, int numTasks) {
        String output = "";
        output += "Got it. I've added this task:\n";
        output += task.toString() + "\n";
        output += "Now you have " + numTasks + " tasks in the list. \n";
        return output;
    }

    /**
     * Prints task has been deleted message.
     *
     * @param task Task that was deleted.
     * @param numTasks Current number of tasks in Task collection. (After Deletion)
     */
    public String taskDelete(Task task, int numTasks) {
        String output = "";
        output += "Noted. I've removed this task:\n";
        output += task.toString() + "\n";
        output += "Now you have " + numTasks + " tasks in the list.\n";
        return output;
    }

    /**
     * Prints task has been marked as complete message.
     *
     * @param task Task that was marked as complete.
     */
    public String markTaskDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task + "\n";
    }

    /**
     * Prints task has been marked as incomplete message.
     *
     * @param task Task that was marked as incomplete.
     */
    public String markTaskUndone(Task task) {
        return "OK, I've marked this task as undone:\n" + task + "\n";
    }

    /**
     * Prints there are no matching task message.
     */
    public String noMatchingTask() {
        return "Sorry! There are no matching tasks in your current list!\n";
    }

    /**
     * Prints existing tasks in given TaskList instance.
     *
     * @param tasks TaskList object whose tasks will be printed.
     */
    public String printTasks(TaskList tasks) {
        return tasks.printTasks();
    }

}
