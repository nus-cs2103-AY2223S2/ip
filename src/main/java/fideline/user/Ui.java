package fideline.user;

import java.util.Scanner;

/**
 * Handles display messages to the user.
 *
 * @author Fun Leon
 */
public class Ui {

    private static final String LINE = "_________________________________________________________________";

    /** Scanner object used to scan user input */
    private Scanner scanner;

    /**
     * Constructs Ui instance.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input.
     *
     * @return The next line of user input as a string.
     */
    public String getNextCommand() {
        return scanner.nextLine();
    }


    /**
     * Displays given message to the user in between two lines.
     *
     * @param message Text to be displayed to the user.
     */
    private static void displayln(String message) {
        System.out.println(message);
    }

    /**
     * Displays a line to the user. Fideline's text is typically
     * sandwiched by two lines for clarity.
     */
    public void showLine() {
        System.out.println(LINE);
    }


    /**
     * Greets the user at the start of the program.
     */
    public void hello() {
        displayln("hello! I'm fideline,\nwhat do you want today?");
    }


    /**
     * Bids the user goodbye.
     */
    public void goodbye() {
        displayln("get out of my sight!");
    }

    /**
     * Tells the user that the list of tasks is currently empty.
     */
    public void emptyListMsg() {
        displayln("eh are you stupid?\nyour list is currently empty!");
    }

    /**
     * Displays list of tasks to the user.
     *
     * @param list String representation of the list of existing tasks.
     */
    public void listMsg(String list) {
        displayln("here! your list:" + list);
    }

    /**
     * Informs the user that Fideline was successful in adding the
     * new task as instructed.
     *
     * @param newTask String representation of the task added.
     * @param taskCount The number of existing tasks currently.
     */
    public void addTaskMsg(String newTask, int taskCount) {
        displayln("ok! i've added to your list:\n  "
                + newTask + "\nwow! there "
                + (taskCount == 1 ? "is " : "are ") + taskCount
                + (taskCount == 1 ? " task " : " tasks ")
                + "in the list now! :0");
    }

    /**
     * Informs the user that Fideline was successful in marking
     * the specified task.
     *
     * @param task String representation of the marked task.
     */
    public void markMsg(String task) {
        displayln("nice work! i've taken note!:\n  " + task);
    }

    /**
     * Informs the user that Fideline was successful in unmarking
     * the specified task.
     *
     * @param task
     */
    public void unmarkMsg(String task) {
        displayln("uhh okay... i've unmarked your task:\n  " + task);
    }

    /**
     * Informs the user that Fideline was successful in deleting
     * the specified task.
     *
     * @param task
     */
    public void deleteMsg(String task, int taskCount) {
        displayln("okay i've deleted this task:\n  " + task
                + "\nnow there " + (taskCount == 1 ? "is " : "are ") + taskCount
                + (taskCount == 1 ? " task " : " tasks ") + "in the list now!");
    }

    /**
     * Informs the user that Fideline was unable to find an existing
     * data file and will make a new one to use instead.
     *
     * @param error String explaining the cause of the issue.
     */
    public void loadError(String error) {
        displayln(error + " i'll just start from scratch");
    }

    /**
     * Informs the user that Fideline ran into an error.
     *
     * @param error String explaining the issue.
     */
    public void showError(String error) {
        displayln("hold up! " + error);
    }

    /**
     * Informs the user that the find command could not find
     * any tasks that contained the given keyword.
     */
    public void emptyFindMsg() {
        displayln("hmm i couldnt find any matching tasks...");
    }

    /**
     * Displays all tasks that were found with the given keyword.
     *
     * @param list Formatted list of all tasks found.
     */
    public void findMsg(String list) {
        displayln("here is everything that matched:" + list);
    }
}
