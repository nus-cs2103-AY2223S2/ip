package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command to quit the Duke program.
 */
public class ByeCommand extends Command {

    @Override
    public boolean isBye() {
        return true;
    }

    /**
     * Executes the command.
     * @param tasks List of tasks.
     * @param ui UI handler.
     * @param storage Storage to save and load list of tasks.
     * @throws DukeException
     * @throws IOException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        storage.saveTasks(tasks);
        return ui.sayBye();
    }
}
