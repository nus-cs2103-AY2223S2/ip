package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to exit Duke.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.save(taskList);
        return "Goodbye! It's been a pleasure talking to you!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
