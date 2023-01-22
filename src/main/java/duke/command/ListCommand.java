package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This class represent's Duke's list function.
 */
public class ListCommand extends Command {

    public ListCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) {
        ui.printList(tasks);
        ui.throwAwayInput();
    }
}
