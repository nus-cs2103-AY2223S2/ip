package duke;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with interactions with the user.
 */
public class Ui {
    private boolean isExit = false;

    /**
     * Prints the line separator.
     */
    public void printLine(){
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        printLine();
        System.out.println("\tHello! I'm C-3PO, Human Cyborg Relations.\n\tWhat can I do for you?");
        printLine();
    }

    /**
     * Prints the goodbye message.
     */
    public void exit() {
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
        isExit = true;
    }

    public boolean getIsExit() {
        return isExit;
    }

    /**
     * Prints the list of tasks.
     *
     * @param listOfTasks Array list of tasks.
     */
    public void listTasks(ArrayList<Task> listOfTasks) {
        Task task;
        printLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 1; i <= listOfTasks.size(); i++) {
            task = listOfTasks.get(i - 1);
            System.out.println("\t" + i + "." + task);
        }
        printLine();
    }

    /**
     * Prints the task that was saved.
     *
     * @param task Task that was saved.
     * @param listOfTasks Array list of tasks.
     */
    public void printSaveTask(Task task, ArrayList<Task> listOfTasks) {
        printLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + listOfTasks.size() + " tasks in the list.");
        printLine();
    }

    /**
     * Prints the task that was marked as done.
     *
     * @param task Task that was marked.
     */
    public void printMarkTask(Task task) {
        printLine();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + task);
        printLine();
    }

    /**
     * Prints the task that was marked as not done.
     *
     * @param task Task that was unmarked.
     */
    public void printUnmarkTask(Task task) {
        printLine();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + task);
        printLine();
    }

    /**
     * Prints the task that was deleted.
     *
     * @param task Task that was deleted.
     * @param listOfTasks Array list of tasks.
     */
    public void printDeleteTask(Task task, ArrayList<Task> listOfTasks) {
        printLine();
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + listOfTasks.size() + " tasks in the list.");
        printLine();
    }

    /**
     * Returns the command input by the user.
     *
     * @return Input command.
     */
    public String readCommand() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine().trim();
        return input;
    }

    /**
     * Prints the error when the data could
     * not be loaded.
     */
    public void showLoadingError() {
        System.out.print("Could not load data from file either due to ");
        System.out.println("incorrect date time format or the file could not be found.");
        System.out.println("Format of the date should be (MMM dd yyyy h:mm a).");
    }

    /**
     * Prints the error when file or folder
     * could not be created.
     */
    public void showFileError() {
        System.out.println("Error occurred when creating the folder/file.");
    }
}
