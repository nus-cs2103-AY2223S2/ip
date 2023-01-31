package dude.ui;

import java.util.List;
import java.util.Scanner;

import dude.task.Task;
import dude.task.TaskList;

/**
 * Handles interactions with user
 */
public class Ui {
    private static Scanner sc = new Scanner(System.in);

    /**
     * Prints horizontal line format for program
     */
    public void showLine() {
        System.out.println(" _______________________________________________________________________");
    }

    /**
     * Prints welcome message to user
     */
    public void showWelcome() {
        String logo = "  _____           __     \n"
                + " |  __ \\ __ __    | | ___  \n"
                + " | |  | | | | |/ _` |/ _ \\\n"
                + " | |__| | |_| | (_| |  __/\n"
                + " |_____/ \\__,_|\\__,_|\\___|\n";

        System.out.println(logo);
        showLine();
        System.out.println("\tEh hello! I'm dude.");
        System.out.println("\tWhat you want me do for you?");
        showLine();
    }

    /**
     * Prints list of task in TaskList for user.
     *
     * @param tasks TaskList to be printed to user.
     */
    public void showList(TaskList tasks) {
        System.out.print(tasks);
    }

    /**
     * Prints add message and added task to user.
     *
     * @param newTask Task added to be printed to user.
     */
    public void showAdd(Task newTask) {
        System.out.println("\tGot it. I've added this task already:");
        System.out.println("\t" + newTask);
        System.out.println("\tNow got " + Task.count + " tasks in your list liao.");
    }

    /**
     * Prints delete message and deleted task to user.
     *
     * @param currentTask Task to be printed to user.
     */
    public void showDelete(Task currentTask) {
        System.out.println("\tOkay can. I've removed this task already:");
        System.out.println("\t" + currentTask);
        System.out.println("\tNow only left with " + Task.count + " tasks in your list liao.");
    }

    /**
     * Prints mark message and marked task to user.
     *
     * @param currentTask Task to be printed to user.
     */
    public void showMark(Task currentTask) {
        System.out.println("\tSwee! I've marked this task as done loh:");
        System.out.println("\t" + currentTask);
    }

    /**
     * Prints unmark message and unmarked task to user.
     *
     * @param currentTask Task to be printed to user.
     */
    public void showUnmark(Task currentTask) {
        System.out.println("\tOkay liar, I've marked this task as undone liao:");
        System.out.println("\t" + currentTask);
    }

    /**
     * Searches the list of task containing a particular keyword
     *
     * @param tasks List of Task for searching
     */
    public void showFind(List<Task> tasks) {
        if (tasks.size() != 0) {
            System.out.println("\tOkay come, here are the task I found containing your keyword: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t" + (i + 1) + "." + tasks.get(i).toString());
            }
        } else {
            System.out.println("\tI cannot find any task containing your keyword sia");
        }

    }

    /**
     * Prints error message to user.
     *
     * @param error Error message to be printed.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Prints goodbye message to user.
     */
    public void showGoodbye() {
        System.out.println("\tCiaos! See you next time.");
    }

    /**
     * Prints loading error message to user.
     */
    public void showLoadingError() {
        System.out.println("\tBzzz.. Sorry ah, I think something went wrong with me, am unable to wake up");
    }

    /**
     * Reads user command input and filters empty line.
     */
    public String readCommand() {
        return sc.nextLine();
    }

}
