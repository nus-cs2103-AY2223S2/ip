package duke;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import duke.tasks.Task;

/**
 * Ui that interacts with the user and displays messages on the screen.
 *
 * @author Cheam Jia Wei
 */
public class Ui {
    private Scanner sc;
    private static final String LINE = "__________________________________________________________\n";

    /**
     * Constructor for the Ui class.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the line of text input by the user.
     *
     * @return The line input by the user as a String.
     */
    public String uiRead() {
        return sc.nextLine();
    }

    /**
     * Message to greet the user.
     */
    public void greet() {
        System.out.println(Ui.LINE
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + Ui.LINE);
    }

    /**
     * Message displayed when program exits.
     */
    public void exit() {
        System.out.println(Ui.LINE
                + "Bye. Hope to see you again soon!\n"
                + Ui.LINE);
    }

    /**
     * Lists out the shows the user the tasks in the TaskList.
     *
     * @param taskList The TaskList containing the tasks to be displayed.
     */
    public void list(TaskList taskList) {
        System.out.println(Ui.LINE + "Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i).toString());
        }
        System.out.println(Ui.LINE);
    }

    /**
     * Displays the task that was marked.
     *
     * @param changed The task that was marked.
     */
    public void mark(Task changed) {
        System.out.println(Ui.LINE + "Nice! I've marked this task as done:\n"
                + changed + "\n" + Ui.LINE);
    }

    /**
     * Displays the task that was unmarked.
     *
     * @param changed The task that was unmarked.
     */
    public void unmark(Task changed) {
        System.out.println(Ui.LINE + "Okay. I've unmarked the following task:\n"
                + changed + "\n" + Ui.LINE);
    }

    /**
     * Displays the task that was added and the number of tasks currently
     * in the TaskList after addition.
     *
     * @param added The task that was added.
     * @param size Number of tasks in the TaskList.
     */
    public void taskAdded(Task added, int size) {
        System.out.println(Ui.LINE + "Got it. I've added this task:\n" + added);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(Ui.LINE);
    }

    /**
     * Displays the task that was deleted from the TaskList and the number
     * of tasks remaining in the TaskList
     *
     * @param removed The task that was deleted.
     * @param size Number of tasks remaining in the TaskList.
     */
    public void delete(Task removed, int size) {
        System.out.println(Ui.LINE + "Noted. I've removed this task:\n" + removed);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(Ui.LINE);
    }

    /**
     * Displays the tasks that are occurring on the specified date.
     *
     * @param input The string input by the user that has the date specified.
     * @param taskList The TaskList containing all the tasks and dates
     */
    public void through(String input, TaskList taskList) {
        LocalDateTime date = LocalDateTime.parse(input);
        int i = 1;
        System.out.println(Ui.LINE + "Here are the tasks occurring through "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ":");
        for (int j = 0; j < taskList.size(); j++) {
            Task t = taskList.get(j);
            if (t.isWithinDate(date)) {
                System.out.println(i + "." + t);
                i++;
            }
        }
        System.out.println(Ui.LINE);
    }

    public void loadError() {
        System.out.println("Task file does not exist");
    }

    public void printError(String e) {
        System.out.println(Ui.LINE + e);
        System.out.println(Ui.LINE);
    }

}
