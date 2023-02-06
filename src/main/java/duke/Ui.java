package duke;

import duke.Tasks.Task;
import duke.Tasks.TaskList;

import java.util.Scanner;

/**
 * Interacts with the user in the form of printing and retrieving information
 */
public class Ui {
    private final Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads string from the terminal
     * @return string input by user
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Prints the welcome page of the chatbot
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    /**
     * Print the delimiter between each command and outputs
     */
    public void showLine() {
        System.out.println("-".repeat(20));
    }

    /**
     * Print string to inform user that the task has been marked
     * @param task the task that has been marked
     */
    public void showMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Print the task that has been added
     * @param task the task that has been added
     * @param tasks the list of all the tasks
     */
    public void showTaskAdded(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints the task that has been deleted
     * @param task the task that has been deleted
     */
    public void showDeleted(Task task) {
        System.out.println("This task has been deleted successfully");
        System.out.println(task);
    }

    /**
     * Formats and Prints the tasks that has been stored
     * @param tasks the list of all the tasks
     */
    public void showStored(TaskList tasks) {
        System.out.println("File has been stored!");
        System.out.println(tasks);
    }

    /**
     * Print the command exception and the command that caused the exception
     * @param word command that the user input
     * @param exception the command exception that was thrown
     */
    public void showCommandError(String word, Exception exception) {
        System.out.println(exception.toString());
        System.out.println("duke.Command: " + word);
    }

    /**
     * Print string to inform the user of a wrong command
     */
    public void showLoadingError() {
        System.out.println("☹ OOPS!!! I'm sorry, but I cannot find the directory!");
    }

    /**
     * Prints the ending
     */
    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints all task in tasks
     * @param tasks list of all tasks
     */
    public void showAll(TaskList tasks) {
        System.out.println(tasks.toFormattedString());
    }
}
