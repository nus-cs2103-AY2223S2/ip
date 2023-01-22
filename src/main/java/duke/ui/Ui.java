package duke.ui;

import java.util.Scanner;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The user interface class.
 * Handles all user input and output.
 */
public class Ui {
    private final Scanner in = new Scanner(System.in);

    /**
     * Prints the welcome message.
     */
    public void printWelcomeMessage() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints the bye message.
     */
    public void printByeMessage() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
        in.close();
    }

    /**
     * Prints the task added message.
     * @param task      The task that was added.
     * @param taskCount The number of tasks in the task list after addition.
     */
    public void printTaskAdded(Task task, int taskCount) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints the task marked message.
     * @param task The task that was marked.
     */
    public void printTaskMarked(Task task) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + task);
    }

    /**
     * Prints the task unmarked message.
     * @param task The task that was unmarked.
     */
    public void printTaskUnmarked(Task task) {
        System.out.println("\tNice! I've marked this task as not done:");
        System.out.println("\t\t" + task);
    }

    /**
     * Prints the task deleted message.
     * @param task      The task that was deleted.
     * @param taskCount The number of tasks in the task list after deletion.
     */
    public void printTaskDeleted(Task task, int taskCount) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints the task list.
     * @param taskList The task list to be printed.
     */
    public void printTaskList(TaskList taskList) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task t = taskList.getTask(i);
            System.out.println("\t\t" + (i + 1) + "." + t);
        }
    }

    /**
     * Prints the error message.
     * @param e The exception thrown.
     */
    public void printError(DukeException e) {
        // System.out.println("\t____________________________________________________________");
        System.out.println("\t" + e.getMessage());
        // System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints the error message for an unknown command.
     */
    public void printErrorActionMessage() {
        // System.out.println("\t____________________________________________________________");
        System.out.println("\tI'm sorry, but I don't know what that means :-(");
        // System.out.println("\t____________________________________________________________");
    }

    /**
     * Reads the command from the user.
     * @return The command entered by the user.
     */
    public String readCommand() {
        return in.nextLine();
    }
}
