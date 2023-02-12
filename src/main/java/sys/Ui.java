package sys;

import command.Command;

import exception.DukeException;

import response.Response;

import task.TaskList;

/**
 * Represents the interface system that the user interacts with.
 */
public class Ui {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;

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
        this.parser = new Parser();
    }

    /**
     * Allows the application to accept commands from the user via standard input.
     *
     * @param input The input given by the user.
     * @return The output from the program.
     */
    public Response send(String input) {
        // Accept input from user.
        try {
            this.showLine();

            // Parse input line.
            Command c = parser.parse(input);

            // Execute the command.
            Response output = c.execute(tasks, this, storage);

            assert output.getMessage() != null : "No message printed";

            System.out.println(output);

            return output;
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return new Response(e.getMessage(), null);
        }
    }

    /**
     * Prints a horizontal line on CLI.
     */
    public void showLine() {
        System.out.println("__________________________________");
    }
}