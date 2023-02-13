package command;

import exception.DukeException;
import exception.InvalidCommandInputException;

import response.Response;

import sys.Storage;
import sys.Ui;

import task.TaskList;

/**
 * Represents the command to mark a task as completed and save the changes.
 */
public class MarkCommand extends Command {

    private String input;

    /**
     * Constructor for MarkCommand
     * @param input Input to parse.
     */
    public MarkCommand(String input) {
        super("mark \\d+");
        this.input = input;
    }

    /**
     * Mark a task in the task list and save the changes.
     *
     * @param tl the current list of tasks
     * @param ui the user interface running.
     * @param storage the storage location for the program.
     * @return Returns response containing all tasks.
     * @throws DukeException If an invalid input is given.
     */
    @Override
    public Response execute(TaskList tl, Ui ui, Storage storage) throws DukeException {

        Integer taskIndex = Integer.valueOf(input.split(" ")[1]) - 1;

        if (taskIndex < 0 || taskIndex >= tl.numberOfTasks()) {
            throw new InvalidCommandInputException("Task number is invalid!", "mark");
        }

        // Mark task and save changes
        tl.markTask(taskIndex);
        storage.save(tl);

        String message = "Nice! I've marked this task as done:\n" + tl.getTask(taskIndex);

        return new Response(message, tl);
    }
}