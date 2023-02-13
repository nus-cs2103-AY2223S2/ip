package command;

import duke.Storage;
import duke.Ui;
import task.Tasklist;

/**
 * The {@code ListCommand} class implements the {@code Command} interface.
 *  It is responsible for displaying the list of tasks stored in the {@code Tasklist}.
 */
public class ListCommand implements Command {
    /**
     * Constructs a new {@code ListCommand}.
     * */
    public ListCommand() {
    }

    /**
     * Executes the {@code ListCommand} by calling the {@code inString()} method
     of the {@code Tasklist} object.
     @param ui the user interface
     @param list the list of tasks
     @param storage the storage backend
     */
    @Override
    public void execute(Ui ui, Tasklist list, Storage storage) {
        list.inString();
    }

    /**
     Returns {@code false} to indicate that the {@code ListCommand} does not exit the program.
     @return {@code false}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
