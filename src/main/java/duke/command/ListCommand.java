package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.gui.Ui;

/**
 * ListCommand - User enters the list command
 */
public class ListCommand extends Command {

    /**
     * Constructor
     */
    public ListCommand() {

    }

    /**
     * Prints out all the tasks.
     * @return the list of tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printList(tasks.getList());
    }
}
