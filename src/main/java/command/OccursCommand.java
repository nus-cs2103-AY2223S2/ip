package command;

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
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        // Handle occurs
        String dateTime = this.input.substring(7);

        // Print tasks that contain deadline
        try {
            tl.printTasksOnDate(dateTime);
        } catch (InvalidDateFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
