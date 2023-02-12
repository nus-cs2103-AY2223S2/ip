package duke.helper;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * Ui class that handles all the interactions with the users
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the exit message and close the scanner
     */
    public String showExit() {
        scanner.close();
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Prints the delete message when a task is removed
     *
     * @param task task to be removed
     * @param size number of remaining tasks
     */
    public static String showDelete(Task task, int size) {
        assert size >= 0 : "Size of TaskList should be zero or more";
        return "Noted. I've removed this task:\n"
                + task
                + String.format("\nNow you have %d tasks in the list.", size);
    }

    /**
     * Prints the message for mark and unmark commands
     *
     * @param isDone whether a task will be marked or unmarked
     * @param taskToMark task to be marked or unmarked
     */
    public String showMark(boolean isDone, Task taskToMark) {
        String output = "";
        if (isDone) {
            output = "Nice! I've marked this task as done:\n";
        } else {
            output = "OK, I've marked this task as not done yet:\n";
        }
        return output + taskToMark;
    }


    /**
     * Prints the message when a task is added
     *
     * @param task task to be added
     * @param size the number of tasks after the task is added
     */
    public String showTaskOutput(Task task, int size) {
        assert size >= 0 : "Size of TaskList should be zero or more";
        return "Got it. I've added this task:\n"
                + task
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * prints the message for searching a file
     *
     * @param taskList List of all the tasks
     */
    public String filter(ArrayList<Task> taskList) {
        String output = "Here are the matching tasks in your list:\n";
        for (Task task : taskList) {
            output += task + "\n";
        }
        return output;
    }
}
