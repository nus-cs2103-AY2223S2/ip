package command;

import exception.DukeException;

import exception.InvalidCommandInputException;
import response.Response;

import sys.Storage;
import sys.Ui;

import task.Task;
import task.TaskList;

/**
 * Represents a command to update a task in the task list.
 */

public class UpdateCommand extends Command {

    public String input;

    /**
     * Constructor for UpdateCommand
     * @param input Input to parse.
     */
    public UpdateCommand(String input) {
        super("update \\d+ .*");
        this.input = input;
    }

    /**
     * Updates a task from the task list and save the changes.
     *
     * @param tl the current list of tasks
     * @param ui the user interface running.
     * @param storage the storage location for the program.
     * @return Response containing remaining tasks.
     * @throws DukeException If an invalid input is given.
     */
    @Override
    public Response execute(TaskList tl, Ui ui, Storage storage) throws DukeException {

        Integer taskIndex = Integer.valueOf(input.split(" ")[1]) - 1;

        if (taskIndex < 0 || taskIndex >= tl.numberOfTasks()) {
            throw new InvalidCommandInputException("Task number is invalid!", "delete");
        }

        assert taskIndex < tl.numberOfTasks() && taskIndex >= 0 : "Index out of range!";

        // Update task and save changes
        Task t = tl.updateTask(taskIndex, input.split(" ", 3)[2]);
        storage.save(tl);

        String message = "Noted. I've updated this task:\n" + t
                + "\nNow you have " + tl.numberOfTasks()
                + (tl.numberOfTasks() > 1 ? " tasks" : " task")
                + " in the list.";

        return new Response(message, tl);
    }
}
