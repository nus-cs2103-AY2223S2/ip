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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveData(tasks);
        setExit(true);
        return ui.showGoodbye();
    }
}
