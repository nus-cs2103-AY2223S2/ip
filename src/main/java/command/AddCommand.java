package command;

import exception.DukeException;

import response.Response;

import sys.Storage;
import sys.Ui;

import task.Task;
import task.TaskList;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {

    private String input;

    /**
     * Constructor for AddCommand.
     * @param input Input to parse.
     */
    public AddCommand(String input) {
        super(".*");
        this.input = input;
    }

    /**
     * Adds a task to the task list and save it.
     *
     * @param tl the current list of tasks
     * @param ui the user interface running.
     * @param storage the storage location for the program.
     * @return Response object containing list of tasks and a message.
     * @throws DukeException If an invalid input is given.
     */
    @Override
    public Response execute(TaskList tl, Ui ui, Storage storage) throws DukeException {

        Task t = tl.addTask(input);
        storage.save(tl);

        String message = "Got it. I've added this task:\n" + t
                + "\nNow you have " + tl.numberOfTasks()
                + (tl.numberOfTasks() > 1 ? " tasks" : " task")
                + " in the list.";

        return new Response(message, tl);
    }
}