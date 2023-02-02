package fideline.user;


import java.util.Scanner;


/**
 * Handles display messages to the user.
 *
 * @author Fun Leon
 */
public class Ui {


    private static final String LINE = "_________________________________________________________________";


    private Scanner scanner;


    public Ui() {
        scanner = new Scanner(System.in);
    }


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


    public void addTaskMsg(String newTask, int taskCount) {
        displayln("ok! i've added to your list:\n  "
                + newTask + "\nwow! there "
                + (taskCount == 1 ? "is " : "are ") + taskCount
                + (taskCount == 1 ? " task " : " tasks ")
                + "in the list now! :0");
    }


    public void markMsg(String task) {
        displayln("nice work! i've taken note!:\n  " + task);
    }


    public void unmarkMsg(String task) {
        displayln("uhh okay... i've unmarked your task:\n  " + task);
    }


    public void deleteMsg(String task, int taskCount) {
        displayln("okay i've deleted this task:\n  " + task
                + "\nnow there " + (taskCount == 1 ? "is " : "are ") + taskCount
                + (taskCount == 1 ? " task " : " tasks ") + "in the list now!");
    }


    public void loadError(String error) {
        displayln(error + " i'll just start from scratch");
    }


    public void showError(String error) {
        displayln("hold up! " + error);
    }


    public void emptyFindMsg() {
        displayln("hmm i couldnt find any matching tasks...");
    }


    public void findMsg(String list) {
        displayln("here is everything that matched:" + list);
    }
}
