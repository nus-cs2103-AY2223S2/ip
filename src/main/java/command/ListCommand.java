package command;

import duke.Storage;
import duke.Ui;
import task.Tasklist;

/**
 * The ListCommand class implements the Command interface.
 *  It is responsible for displaying the list of tasks stored in the Tasklist.
 */
public class ListCommand implements Command {
    /**
     * Constructor that creates a new ListCommand instance.
     * */
    public ListCommand() {
    }

    /**
     * * Executes the ListCommand by calling the inString() method
     * of the Tasklist object.
     * @param ui the user interface
     * @param list the list of tasks
     * @param storage the storage backend
     */
    @Override
    public String execute(Ui ui, Tasklist list, Storage storage) {
        return ui.getListReply(list);
    }
}
