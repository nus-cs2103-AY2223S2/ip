package alfred.command;

import alfred.exceptions.AlfredException;
import alfred.storage.Storage;
import alfred.task.TaskList;
import alfred.ui.Ui;

/**
 * Represents an exit command when a user wishes to exit.
 */
public class ExitCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlfredException {
        storage.write(tasks);
        ui.displayBye();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true; // does this part contradict LSP? but command is an abstract class
    }
}
