package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an Exit command.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks.getList());
        return ui.terminate();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
