package command;

import response.Response;

import sys.Storage;
import sys.Ui;

import task.TaskList;

/**
 * Represents the command to exit from the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructor for ExitCommand
     */
    public ExitCommand() {
        super("bye");
    }

    /**
     * Prints an exit message and exits from the program.
     * @param tl the current list of tasks
     * @param ui the user interface running.
     * @param storage the storage location for the program.
     * @return Response containing exit message.
     */
    @Override
    public Response execute(TaskList tl, Ui ui, Storage storage) {

        System.out.println("Bye. Hope to see you again soon!");

        System.exit(0);

        return new Response("EXIT", tl);
    }
}