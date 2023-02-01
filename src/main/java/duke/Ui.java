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

    public void showLine() {
        System.out.println(" ".repeat(4)
                + "_".repeat(60));
    }

    public void echo(String message) {
        String indent = " ".repeat(5);
        System.out.println(indent
                + message.replace("\n",
                "\n" + indent));
    }

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

    public void showFarewell() {
        echo("Bye. Hope to see you again soon!");
    }

    public void showTask(Task task) {
        echo("  " + task.toString());
    }

    public void showTaskCount(TaskList tasks) {
        echo("Now you have "
                + tasks.size()
                + " tasks in the list.");
    }

    public void showTasks(TaskList tasks) throws DukeException {
        StringBuilder message = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            message.append("\n")
                    .append(i + 1)
                    .append(".")
                    .append(tasks.get(i));
        }
        echo(message.toString());
    }

    // Error messages for exceptions specific to Duke.
    public void showError(String errorMessage) {
        echo("☹ OOPS!!! " + errorMessage + " :-(");
    }

    public void showLoadingError() {
        showError("There was an error while loading from save file");
    }
}
