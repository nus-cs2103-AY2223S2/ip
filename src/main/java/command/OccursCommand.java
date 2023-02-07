package command;

import exception.DukeException;
import exception.InvalidDateFormatException;

import response.Response;

import sys.Storage;
import sys.Ui;

import task.TaskList;

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
        super("occurs .*");
        this.input = input;
    }

    /**
     * Lists all tasks that occur within the given period.
     *
     * @param tl the current list of tasks
     * @param ui the user interface running.
     * @param storage the storage location for the program.
     * @return Returns filtered tasks.
     * @throws DukeException If an invalid input is given.
     */
    @Override
    public Response execute(TaskList tl, Ui ui, Storage storage) throws DukeException {

        String dateTime = input.substring(7);

        try {
            String message = "Found the following tasks: ";
            TaskList tasks = tl.findTasksOnDate(dateTime);

            return new Response(message, tasks);
        } catch (InvalidDateFormatException e) {
            return new Response(e.getMessage(), tl);
        }
    }
}