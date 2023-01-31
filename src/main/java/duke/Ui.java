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
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Display the given list of tasks.
     * 
     * @param list the list of tasks to display.
     */
    public void showList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1) + ". " + list.get(i));
        }
    }

    /**
     * Display the successful marking of a task.
     * 
     * @param task the task that was marked.
     */
    public void showMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Display the successful unmarking of a task.
     * 
     * @param task the task that was unmarked.
     */
    public void showUnmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Display the successful addition of a task.
     * 
     * @param task the task that was added.
     * @param size the size of the list after the addition.
     */
    public void showAdd(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Display the successful deletion of a task.
     * 
     * @param task the task that was deleted.
     * @param size the size of the list after the deletion.
     */
    public void showDelete(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showFind(TaskList list) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1) + ". " + list.get(i));
        }
    }

    public void showLoadingError() {
        System.out.println("Error loading file!");
    }

    /**
     * Display the error message for saving the file.
     */
    public void showSavingError() {
        System.out.println("Error saving file!");
    }

    /**
     * Display the error message for the given exception.
     * 
     * @param message the error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
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