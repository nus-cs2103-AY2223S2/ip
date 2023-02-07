package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.gui.Ui;

/**
 * ListCommand - User enters the list command
 */
public class ListCommand extends Command {

    /**
     * Public constructor
     */
    public ListCommand() {

    }

    /**
     * Prints out all the tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printList(tasks.getList());
    }
}
