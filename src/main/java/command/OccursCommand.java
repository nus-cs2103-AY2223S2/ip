package command;

import response.Response;
import sys.Ui;
import sys.Storage;

import task.TaskList;

import exception.DukeException;
import exception.InvalidDateFormatException;

/**
 * Represents the command to list all the tasks that occur within the given period.
 */
public class OccursCommand extends Command {
    private String input;

    /**
     * Constructor for OccursCommand
     * @param input Input to parse.
     */
    public OccursCommand(String input) {
        super("occurs ");
        this.input = input;
    }

    /**
     * Lists all tasks that occur within the given period.
     *
     * @param tl the current list of tasks
     * @param ui the user interface running.
     * @param storage the storage location for the program.
     * @throws DukeException If an invalid input is given.
     * @return Returns filtered tasks.
     */
    @Override
    public Response execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        // Handle occurs
        String dateTime = this.input.substring(7);

        try {
            String message = "Found the following tasks: ";
            TaskList tasks = tl.findTasksOnDate(dateTime);

            return new Response(message, tasks);
        } catch (InvalidDateFormatException e) {
            return new Response(e.getMessage(), tl);
        }
    }
}
