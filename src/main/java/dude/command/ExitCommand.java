package dude.command;

import dude.storage.Storage;
import dude.task.TaskList;
import dude.ui.Ui;

/**
 * Command to exit Dude program
 */
public class ExitCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveData(tasks);
        ui.showGoodbye();
        setExit(true);
    }
}
