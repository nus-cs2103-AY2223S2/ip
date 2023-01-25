package duke.ui;

import java.util.Scanner;

import duke.tasklist.TaskList;
import duke.tasktypes.Task;

/**
 * Represents a User Interface (Ui) instance.
 */
public class Ui {

    /** Scanner Object for reading User input. */
    public Scanner sc;

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
    public void greet() {
        String greeting = "What's up! XyDuke here!\nHow can I be of assistance?";
        System.out.println(greeting);
    }

    /**
     * Prints Duke's goodbye message.
     */
    public void goodbye() {
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(goodbye);
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
    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints directory created message.
     */
    public void directoryCreate() {
        System.out.println("Data folder created!");
    }

    /**
     * Prints file created message.
     */
    public void fileCreate() {
        System.out.println("Duke data file: duke.txt created!");
    }

    /**
     * Prints data is being saved message.
     */
    public void uploading() {
        System.out.println("Updating your data. Please wait..");
    }

    /**
     * Prints data has been saved message.
     */
    public void saved() {
        System.out.println("All changes saved successfully!");
    }

    /**
     * Prints task has been added message.
     *
     * @param task Task that was added.
     * @param numTasks Current number of tasks in Task collection. (After addition)
     */
    public void taskAdd(Task task, int numTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    /**
     * Prints task has been deleted message.
     *
     * @param task Task that was deleted.
     * @param numTasks Current number of tasks in Task collection. (After Deletion)
     */
    public void taskDelete(Task task, int numTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    /**
     * Prints task has been marked as complete message.
     *
     * @param task Task that was marked as complete.
     */
    public void markTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Prints task has been marked as incomplete message.
     *
     * @param task Task that was marked as incomplete.
     */
    public void markTaskUndone(Task task) {
        System.out.println("OK, I've marked this task as undone:\n" + task);
    }

    /**
     * Prints there are no matching task message.
     */
    public void noMatchingTask() {
        System.out.println("Sorry! There are no matching tasks in your current list!");
    }

    /**
     * Prints existing tasks in given TaskList instance.
     *
     * @param tasks TaskList object whose tasks will be printed.
     */
    public void printTasks(TaskList tasks) {
        tasks.printTasks();
    }

}
