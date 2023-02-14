package wtd;

import java.util.Scanner;

import wtd.task.Task;

/**
 * Ui class to handle user interaction.
 */
public class Ui {
    /** Scanner to read user input. */
    private Scanner sc;

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays welcome message.
     */
    public String getWelcome() {
        return "Hey, why did you wake me up?\nI am the great Watt Toodu.\nDon't mess with me!\n";
    }

    /**
     * Displays line to separate user input and output.
     */
    public void showLine() {
        System.out.println("----------------------------------------");
    }

    /**
     * Displays goodbye message.
     */
    public String showBye() {
        return "Good riddance...\n";
    }

    /**
     * Displays the given list of tasks.
     * 
     * @param list the list of tasks to display.
     */
    public String showList(TaskList list) {
        String ret = list.stream()
            .map(task -> (list.indexOf(task) + 1) + ". " + task + "\n")
            .reduce("Here! Now leave me alone...\n", (a, b) -> a + b);
        return ret;
    }

    /**
     * Displays the successful marking of a task.
     * 
     * @param task the task that was marked.
     */
    public String showMark(Task task) {
        return "Wow, reaaal impressive work.\n" + task + "\n";
    }

    /**
     * Displays the successful unmarking of a task.
     * 
     * @param task the task that was unmarked.
     */
    public String showUnmark(Task task) {
        return "So did you complete it or not?!?\n" + task + "\n";
    }

    /**
     * Displays the successful addition of a task.
     * 
     * @param task the task that was added.
     * @param size the size of the list after the addition.
     */
    public String showAdd(Task task, int size) {
        return "Clearly, having good memory isn't one of your strengths...\n" + task + "\nNow you have " + size + " tasks in the list.\n";
    }

    /**
     * Displays the successful deletion of a task.
     * 
     * @param task the task that was deleted.
     * @param size the size of the list after the deletion.
     */
    public String showRemove(Task task, int size) {
        return "Please clean up after yourself:\n" + task + "\nNow you have " + size + " tasks in the list.\n";
    }

    public String showFind(TaskList list) {
        String ret = list.stream()
            .map(task -> (list.indexOf(task) + 1) + ". " + task + "\n")
            .reduce("Needle in a haystack...\n", (a, b) -> a + b);
        return ret;
    }

    /**
     * Displays the succesful addition of a command alias.
     * 
     * @param existing the existing command.
     * @param alias the alias of the command.
     */
    public String showSet(String existing, String alias) {
        return "Po-tay-toes, po-ta-toes, \"" + alias + "\" is now the same as \"" + existing + "\".\n";
    }

    /**
     * Displays the error message for loading the file.
     */
    public String showLoadingError() {
        return "Error loading file!\n";
    }

    /**
     * Displays the error message for saving the file.
     */
    public String showSavingError() {
        return "Error saving file!\n";
    }

    /**
     * Displays the error message for the given exception.
     * 
     * @param message the error message to display.
     */
    public String showError(String message) {
        return message + "\n";
    }

    /**
     * Reads the user input.
     * 
     * @return the user input string.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}