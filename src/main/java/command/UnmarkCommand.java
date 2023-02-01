package command;

import exception.DukeException;
import exception.InvalidCommandInputException;

import response.Response;

import sys.Storage;
import sys.Ui;

import task.TaskList;

/**
 * Represents the command to unmark a task and save the changes.
 */
public class UnmarkCommand extends Command {
    private String input;

    /**
     * Constructor for UnmarkCommand
     * @param input Input to parse.
     */
    public UnmarkCommand(String input) {
        super("unmark \\d+");
        this.input = input;
    }

    /**
     * Unmarks a task and save the changes.
     *
     * @param tl the current list of tasks
     * @param ui the user interface running.
     * @param storage the storage location for the program.
     * @return Returns remaining tasks.
     * @throws DukeException If an invalid input is given.
     */
    @Override
    public Response execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        // Get index to unmark
        Integer idx = Integer.valueOf(input.split(" ")[1]) - 1;

        // Verify if task number is invalid:
        if (idx < 0 || idx >= tl.numberOfTasks()) {
            throw new InvalidCommandInputException("task.Task number is invalid!", "unmark");
        }

        // Unmark task and save changes
        tl.unmarkTask(idx);
        storage.save(tl);

        // Print statement
        String message = "OK, I've marked this task as not done yet:\n" + tl.getTask(idx);

        return new Response(message, tl);
    }
}