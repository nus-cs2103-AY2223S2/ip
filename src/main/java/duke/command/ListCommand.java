package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This class represent's Duke's list function.
 */
public class ListCommand extends Command {

    /** Constructs the list command. */
    public ListCommand() {}

    /**
     * Informs the user of the tasks in tasklist.
     *
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) {
        ui.throwAwayInput();
        return ui.printList(tasks);
    }
}
