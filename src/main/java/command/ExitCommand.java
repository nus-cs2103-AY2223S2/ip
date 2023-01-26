package command;

import exception.DukeException;
import sys.Ui;
import sys.Storage;

import task.TaskList;

/**
 * Represents the command to exit from the program.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super("bye");
    }

    /**
     * Prints an exit message and exits from the program.
     *
     * @param tl the current list of tasks
     * @param ui the user interface running.
     * @param storage the storage location for the program.
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        // Print goodbye statement
        System.out.println("Bye. Hope to see you again soon!");

        // Exit program
        System.exit(0);
    }
}
