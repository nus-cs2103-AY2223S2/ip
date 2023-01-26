package command;

import exception.DukeException;
import sys.Ui;
import sys.Storage;

import task.TaskList;

/**
 * Represents the command to list all the tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand
     */
    public ListCommand() {
        super("list");
    }

    /**
     * Lists all tasks that exist in the task list.
     *
     * @param tl the current list of tasks
     * @param ui the user interface running.
     * @param storage the storage location for the program.
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        // Print statement
        System.out.println(tl.toString());
    }
}
