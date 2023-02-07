package duke;

import java.util.Scanner;

import duke.task.Task;

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
     * Display welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Display line to separate user input and output.
     */
    public void showLine() {
        System.out.println("----------------------------------------");
    }

    /**
     * Display goodbye message.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Display the given list of tasks.
     * 
     * @param list the list of tasks to display.
     */
    public String showList(TaskList list) {
        String ret = list.stream()
            .map(task -> (list.indexOf(task) + 1) + ". " + task + "\n")
            .reduce("Here are the tasks in your list:\n", (a, b) -> a + b);
        return ret;
    }

    /**
     * Display the successful marking of a task.
     * 
     * @param task the task that was marked.
     */
    public String showMark(Task task) {
        return "Nice! I've marked this task as done:\n" + task + "\n";
    }

    /**
     * Display the successful unmarking of a task.
     * 
     * @param task the task that was unmarked.
     */
    public String showUnmark(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task + "\n";
    }

    /**
     * Display the successful addition of a task.
     * 
     * @param task the task that was added.
     * @param size the size of the list after the addition.
     */
    public String showAdd(Task task, int size) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + size + " tasks in the list.\n";
    }

    /**
     * Display the successful deletion of a task.
     * 
     * @param task the task that was deleted.
     * @param size the size of the list after the deletion.
     */
    public String showRemove(Task task, int size) {
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + size + " tasks in the list.\n";
    }

    public String showFind(TaskList list) {
        String ret = list.stream()
            .map(task -> (list.indexOf(task) + 1) + ". " + task + "\n")
            .reduce("Here are the matching tasks in your list:\n", (a, b) -> a + b);
        return ret;
    }

    /**
     * Display the succesful addition of a command alias.
     * 
     * @param existing the existing command.
     * @param alias the alias of the command.
     */
    public String showSet(String existing, String alias) {
        return "Got it. I've added the alias \"" + alias + "\" for the command \"" + existing + "\".\n";
    }

    /**
     * Display the error message for loading the file.
     */
    public String showLoadingError() {
        return "Error loading file!\n";
    }

    /**
     * Display the error message for saving the file.
     */
    public String showSavingError() {
        return "Error saving file!\n";
    }

    /**
     * Display the error message for the given exception.
     * 
     * @param message the error message to display.
     */
    public String showError(String message) {
        return message + "\n";
    }

    /**
     * Read the user input.
     * 
     * @return the user input string.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}