package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a ListCommand that implements the interface Command.
 */
public class ListCommand implements Command {

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.displayTasks(tasks);
    }
}
