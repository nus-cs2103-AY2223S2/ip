package userinteraction;

import store.TaskList;
import task.Task;

import java.util.Scanner;

/**
 * Handles all user interaction.
 */
public class Ui {
    /**
     * Greeting message.
     */
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Divides each user input.
     */
    private final String DIVIDER_LINE = "_________________________________________________\n";
    /**
     * Bye message.
     */
    private final String BYE_MSG = "Bye. Hope to see you again soon!";

    /**
     * Scanner for user input.
     */
    private final Scanner scanner;

    /**
     * Public constructor.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads user input.
     *
     * @return Input from user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints welcome message.
     */
    public void printWelcomeMsg() {
        System.out.println("Hello from\n" + LOGO + "\nWhat can I do for you?");
    }

    /**
     * Prints divider line.
     */
    public void printLine() {
        System.out.println(DIVIDER_LINE);
    }

    /**
     * Prints task added succesfully.
     *
     * @param taskList Stores all tasks.
     * @param task Task user just added.
     */
    public void printAddTaskMsg(TaskList taskList, Task task) {
        System.out.println("Got it. I've added this task:\n  " +
                task.toString() + "\n Now you have " + taskList.getSize() + " tasks in the list");
    }

    /**
     * Prints task marked or unmarked successfully.
     *
     * @param isMarked Boolean for whether task is marked or unamrked.
     * @param task Task specified by user.
     */
    public void printMarkTaskMsg(boolean isMarked, Task task) {
        if (isMarked) {
            System.out.println("Nice! I've marked this task as done: \n" + "[x] " + task.getStr());
        } else {
            System.out.println("OK, I've marked this task as not done yet: \n" + "[ ] " + task.getStr());
        }
    }

    /**
     * Prints task deleted successfully.
     *
     * @param task Task specified by user.
     * @param size Number of tasks left in the taskList.
     */
    public void printDeleteTaskMsg(Task task, int size) {
        System.out.println("Noted. I've removed this task:\n  " +
                task.toString() +
                "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Prints bye message.
     */
    public void printByeMsg() {
        System.out.println(BYE_MSG);
    }

}
