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
     * Returns Duke's greeting message.
     */
    public String greet() {
        return "What's up! XyDuck here!\nHow can I be of assistance?\n";
    }

    /**
     * Returns Duke's goodbye message.
     *
     * @return Duke's goodbye message.
     */
    public String goodbye() {
        return "Bye. Hope to see you again soon!\n";
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
     * Returns error message from exception instance.
     *
     * @param e Exception object whose message is to be printed.
     * @return Error message from exception.
     */
    public String showError(Exception e) {
        return e.getMessage();
    }

    /**
     * Returns directory created message.
     *
     * @return Directory created message.
     */
    public String directoryCreate() {
        return "Data folder created!\n";
    }

    /**
     * Returns file created message.
     *
     * @return Data file created message.
     */
    public String fileCreate() {
        return "Duke data file: duke.txt created!\n";
    }

    /**
     * Returns data is being saved message.
     *
     * @return Data is being saved message.
     */
    public String uploading() {
        return "Updating your data. Please wait..\n";
    }

    /**
     * Returns data has been saved message.
     *
     * @return Data has been saved message.
     */
    public String saved() {
        return "All changes saved successfully!\n";
    }

    /**
     * Returns task has been added message.
     *
     * @param task Task that was added.
     * @param numTasks Current number of tasks in Task collection. (After addition)
     * @return Task has been added message.
     */
    public String taskAdd(Task task, int numTasks) {
        String output = "";
        output += "Got it. I've added this task:\n";
        output += task.toString() + "\n";
        output += "Now you have " + numTasks + " tasks in the list. \n";
        return output;
    }

    /**
     * Returns task has been deleted message.
     *
     * @param task Task that was deleted.
     * @param numTasks Current number of tasks in Task collection. (After Deletion)
     * @return Task has been deleted message.
     */
    public String taskDelete(Task task, int numTasks) {
        String output = "";
        output += "Noted. I've removed this task:\n";
        output += task.toString() + "\n";
        output += "Now you have " + numTasks + " tasks in the list.\n";
        return output;
    }

    /**
     * Returns task has been marked as complete message.
     *
     * @param task Task that was marked as complete.
     * @return Task has been marked as complete message.
     */
    public String markTaskDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task + "\n";
    }

    /**
     * Returns task has been marked as incomplete message.
     *
     * @param task Task that was marked as incomplete.
     * @return Task has been marked as incomplete message.
     */
    public String markTaskUndone(Task task) {
        return "OK, I've marked this task as undone:\n" + task + "\n";
    }

    /**
     * Returns there are no matching task message.
     *
     * @return No matching tasks message.
     */
    public String noMatchingTask() {
        return "Sorry! There are no matching tasks in your current list!\n";
    }

    /**
     * Returns existing tasks in given TaskList instance.
     *
     * @param tasks TaskList object whose tasks will be printed.
     * @return String representation of existing tasks in TaskList instance.
     */
    public String printTasks(TaskList tasks) {
        return tasks.printTasks();
    }

    /**
     * Returns existing tasks in given TaskList instance.
     * Tasks are ordered by deadline.
     *
     * @param tasks TaskList object whose tasks will be printed.
     * @return String representation of existing tasks in TaskList instance.
     */
    public String printTasksOrdered(TaskList tasks) {
        return tasks.printTasksInOrder();
    }

}
