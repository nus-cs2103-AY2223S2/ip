package duke.helper;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * Ui class that handles all the interactions with the users
 */
public class Ui {
    private final String line = "_______________________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public static void showErrorMsg(String type) {
        System.out.println(String.format("%s commands need to be followed by an integer!", type));
    }

    public static void showErrorMsg(int size) {
        System.out.println(String.format("Sorry but there are only %d tasks stored!", size));
    }

    public String[] getNextLine() {
        showLine();
        String input = scanner.nextLine();
        String[] splitStr = input.split(" ", 2);
        showLine();
        return splitStr;
    }

    /**
     * Prints the welcome message when the program starts running
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints a horizontal line
     */
    public void showLine() {
        System.out.println(line);
    }

    /**
     * Prints the exit message and close the scanner
     */
    public void showExit() {
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
        this.showLine();
    }

    /**
     * Prints the delete message when a task is removed
     *
     * @param task task to be removed
     * @param size number of remaining tasks
     */
    public static void showDelete(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", size));
    }

    /**
     * Prints the message for mark and unmark commands
     *
     * @param isDone whether a task will be marked or unmarked
     * @param taskToMark task to be marked or unmarked
     */
    public void showMark(boolean isDone, Task taskToMark) {
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(taskToMark);
    }

    /**
     * Prints the message when a task is added
     *
     * @param task task to be added
     * @param size the number of tasks after the task is added
     */
    public static void showTaskOutput(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * prints the message for searching a file
     *
     * @param taskList List of all the tasks
     */
    public static void filter(ArrayList<Task> taskList) {
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : taskList) {
            System.out.println(task);
        }
    }
}
