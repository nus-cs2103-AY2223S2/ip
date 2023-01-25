package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
    }

    @Override
    public boolean isGoodbye() {
        return true;
    }

    /**
     * Saves existing state of task collection to data file.
     *
     * @param tasks Tasklist object.
     * @param storage Storage object.
     * @param ui Ui Object.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        storage.save(tasks);
        ui.goodbye();
        ui.close();
    }
}
