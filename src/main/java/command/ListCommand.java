package command;

import response.Response;

import sys.Storage;
import sys.Ui;

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
     * @return Returns response containing task list.
     */
    @Override
    public Response execute(TaskList tl, Ui ui, Storage storage) {

        String message = "Here are your tasks!";

        return new Response(message, tl);
    }
}