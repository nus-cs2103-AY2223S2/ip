package ui;

import java.util.ArrayList;

import task.Task;

/**
 * Class to encapsulate most outputs to console.
 * To be modified in order to encapsulate inputes in console.
 */
public class Ui {
    private boolean isStarted;

    /**
     * Constructor for Ui Object.
     */
    public Ui() {
        isStarted = true;
        startUpSequence();
    }

    /**
     * Outputs startup sequence to console.
     */
    public void startUpSequence() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Outputs loading error to console.
     */
    public void showLoadingError() {
        System.out.println("Error loading file, proceeding to rewrite the file");
    }

    /**
     * Outputs AddedMessage to console, given task that was added.
     *
     * @param item Task that was added.
     */
    public static void showAddedMessage(Task item) {
        System.out.println("    Duke says:");
        System.out.println("    Added");
        System.out.println("    " + item.toString());
    }

    /**
     * Outputs RemovedMessage to console, given Task that was removed.
     *
     * @param item Task that was removed.
     */
    public static void showRemovedMessage(Task item) {
        System.out.println("    Noted. I have removed this task:");
        System.out.println("    " + item.toString());
    }

    /**
     * Outputs MarkedMessage to console, given Task that was marked as done.
     *
     * @param item Task that was marked as done.
     */
    public static void showMarkedMessage(Task item) {
        System.out.println("    OK I've marked this task as done: ");
        System.out.println("    " + item.toString());
    }

    /**
     * Outputs UnmarkedMessage to console, given Task that was marked as undone.
     *
     * @param item Task that was marked as undone.
     */
    public static void showUnmarkedMessage(Task item) {
        System.out.println("    OK I've marked this task as not done yet:");
        System.out.println("    " + item.toString());
    }

    /**
     * Outputs SavedMessage to console, demonstrating completion of save to hard drive.
     */
    public void showSavedMessage() {
        System.out.println("    Save complete");
    }

    /**
     * Outputs ClosingMessage to console, demonstrating exit of program.
     */
    public void showClosingMessage() {
        System.out.println("    Duke says:");
        System.out.println("    Bye. Hope you run this program again!");
    }

    /**
     * Prints the whole list of tasks given ArrayList of tasks.
     * @param list ArrayList of Tasks to be printed.
     */
    public void printListNumber(ArrayList<Task> list) {
        System.out.println("    Now you have " + list.size() + " task(s) in the list.");
    }

    /**
     * Outputs SavingMessage, demonstrating the commencement of saving.
     */
    public void showSavingMessage() {
        System.out.println("    Saving:");
    }

    /**
     * Given an ArrayList, print all tasks in it.
     * @param list ArrayList of which contents to be printed.
     */
    public static void printArrayList(ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("    " + (i + 1) + ". "
                    + list.get(i).toString());
        }
    }
}
