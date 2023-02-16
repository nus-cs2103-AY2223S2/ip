package userinteraction;

import java.util.Scanner;

import store.TaskList;
import task.Task;

/**
 * Handles all user interaction.
 */
public class Ui {
    /**
     * Greeting message.
     */
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Divides each user input.
     */
    private static final String DIVIDER_LINE = "_________________________________________________\n";
    /**
     * Bye message.
     */
    private static final String BYE_MSG = "Bye. Hope to see you again soon!";

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
    public String getWelcomeMsg() {
        return "Hello from\n" + LOGO + "\nWhat can I do for you?";
    }

    public void printWelcomeMsg() {
        System.out.println(getWelcomeMsg());
    }

    /**
     * Prints divider line.
     */
    public void printLineString() {
        System.out.println(DIVIDER_LINE);
    }

    /**
     * Prints task added succesfully.
     *
     * @param taskList Stores all tasks.
     * @param task Task user just added.
     */
    public String getAddTaskMsg(TaskList taskList, Task task) {
        return "Got it. I've added this task:\n  "
                + task.toString() + "\n Now you have " + taskList.getSize() + " tasks in the list";
    }

    /**
     * Prints task marked or unmarked successfully.
     *
     * @param isMarked Boolean for whether task is marked or unamrked.
     * @param task Task specified by user.
     */
    public String getMarkTaskMsg(boolean isMarked, Task task) {
        if (isMarked) {
            return "Nice! I've marked this task as done: \n" + "[x] " + task.getStr();
        } else {
            return "OK, I've marked this task as not done yet: \n" + "[ ] " + task.getStr();
        }
    }

    /**
     * Prints task deleted successfully.
     *
     * @param task Task specified by user.
     * @param size Number of tasks left in the taskList.
     */
    public String getDeleteTaskMsg(Task task, int size) {
        return "Noted. I've removed this task:\n  "
                + task.toString()
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Prints bye message.
     */
    public String getByeMsg() {
        return BYE_MSG;
    }

}
