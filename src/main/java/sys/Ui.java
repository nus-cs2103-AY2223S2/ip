package sys;

import command.Command;

import exception.DukeException;

import task.TaskList;

import java.util.Scanner;

/**
 * Represents the interface system that the user interacts with.
 */
public class Ui {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for UI.
     */
    public Ui() {}

    /**
     * Sets the context for the program.
     *
     * @param storage The storage area used by the application.
     * @param tasks The task list used by the application.
     */
    public void setContext(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Allows the application to accept commands from the user via standard input.
     */
    public void acceptInput() {

        // Print welcome message.
        System.out.println("Hello! I'm Duke\n What can I do for you?");

        // Create Scanner and Parser object.
        Scanner sc = new Scanner(System.in);
        Parser p = new Parser(this.tasks, this.storage, this);

        // Accept input from user.
        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                this.showLine();

                // Parse input line.
                Command c = p.parse(input);

                // Execute the command.
                c.execute(this.tasks, this, this.storage);
            } catch (DukeException e) {
                showError(e.getMessage());
            } finally {
                this.showLine();
            }
        }
    }

    /**
     * Prints the given error message.
     *
     * @param e The error message.
     */
    public void showError(String e) {
        System.out.println("ERROR: " + e);
    }

    /**
     * Prints a horizontal line to partition the application inputs and outputs.
     */
    public void showLine() {
        System.out.println("__________________________________");
    }
}
