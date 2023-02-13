package henz.ui;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import henz.henzexception.HenzException;
import henz.tasks.Task;

/**
 * The Ui class handles all the UI related operations, such as printing
 * messages, displaying errors and dividing lines.
 * It also reads commands from the user and prints out the list of tasks.
 * @author Leng Wei Cong, Justin
 */
public class Ui {
    /**
     * Scanner object for reading user commands.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Displays divider line on the console.
     */
    public void showDivider() {
        System.out.println("\n_______________________________\n");
    }

    /**
     * Displays error message on the console.
     * @param error the error caught
     */
    public void showError(HenzException error) {
        System.out.println(error.getMessage());
    }

    /**
     * Displays loading error on the console.
     */
    public void showLoadingError() {

    }

    /**
     * Displays welcome message on the console.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Henz\nWhat can I do for you?\n");

    }

    /**
     * Displays exit message on the console.
     */
    public void showExit() {
        this.close();
        System.out.println("toodeloo!\n");
    }

    /**
     * Reads user command.
     * @return user input
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Displays message on the console when task is added.
     * @param task      the task that is being added
     * @param tasksSize the size of the taskList
     */
    public void showTaskAdded(Task task, int tasksSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasksSize + " tasks in the list.");
    }

    /**
     * Displays message on the console when task is removed.
     * @param task      the task that is being added
     * @param tasksSize the size of the taskList
     */
    public void showTaskRemoved(Task task, int tasksSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasksSize + " tasks in the list.");
    }

    /**
     * Displays the a list of task from taskList on the console.
     * @param tasks the taskList
     */
    public void showTaskList(List<Task> tasks) {
        IntStream.range(0, tasks.size())
                .forEach((index) -> {
                    System.out.println((index + 1) + ". " + tasks.get(index));
                });
    }

    /**
     * Displays message on the console.
     * @param message
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
