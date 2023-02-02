package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    protected Scanner sc;

    /**
     * Creates a user interface that interacts with the user.
     */
    public Ui() {
        sc = new Scanner(System.in);

    }

    /**
     * Reads the command input by the user
     *
     * @return The full command input by the user
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows the divider line ("_______")
     */
    public void showLine() {
        System.out.println(" ".repeat(4)
                + "_".repeat(60));
    }

    /**
     * Shows the specified message
     *
     * @param message Message to be shown
     */
    public void echo(String message) {
        String indent = " ".repeat(5);
        System.out.println(indent
                + message.replace("\n",
                "\n" + indent));
    }

    /**
     * Shows Duke's logo and greets the user
     */
    public void showWelcome() {
        // Shows logo of Duke
        System.out.println("Hello from\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n");

        // Shows user greeting
        showLine();
        echo("Hello! I'm Duke\n"
                + "What can I do for you?");
        showLine();
    }

    /**
     * Shows farewell message
     */
    public void showFarewell() {
        echo("Bye. Hope to see you again soon!");
    }

    /**
     * Shows the specified task's details
     *
     * @param task Task to be shown
     */
    public void showTask(Task task) {
        echo("  " + task.toString());
    }

    /**
     * Shows the number of tasks stored in list
     *
     * @param tasks List of tasks
     */
    public void showTaskCount(TaskList tasks) {
        echo("Now you have "
                + tasks.size()
                + " tasks in the list.");
    }

    /**
     * Shows the list of task
     *
     * @param tasks List of tasks
     */
    public void showTasks(TaskList tasks) throws DukeException {
        if (tasks.size() == 0) {
            echo("There is no task in your list");
            return;
        }

        StringBuilder message = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            message.append("\n")
                    .append(i + 1)
                    .append(".")
                    .append(tasks.get(i));
        }
        echo(message.toString());
    }

    /**
     * Shows the list of tasks that contains the specified keyword
     *
     * @param tasks List of tasks
     * @param keyword Keyword
     */
    public void showFind(TaskList tasks, String keyword) {
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list:");
        int i = 0;
        for (Task task: tasks.getTasks()) {
            if (task.getDescription()
                    .toUpperCase()
                    .contains(keyword
                            .toUpperCase())) {
                message.append("\n")
                        .append(i + 1)
                        .append(".")
                        .append(task);
                i++;
            }
        }
        if (i == 0) {
            message = new StringBuilder("There is no matching task in your list");
        }
        echo(message.toString());
    }

    // Error messages for exceptions specific to Duke.
    /**
     * Shows the error message of the encountered exception
     *
     * @param errorMessage Error message
     */
    public void showError(String errorMessage) {
        echo("☹ OOPS!!! " + errorMessage + " :-(");
    }

    /**
     * Shows the error message of the exception encountered when loading tasks
     * from save data
     */
    public void showLoadingError() {
        showError("There was an error while loading from save file");
    }
}
