package nemo.command;

import nemo.storage.Storage;
import nemo.task.TaskList;
import nemo.ui.Ui;

/**
 * Command to terminate Nemo application.
 *
 * @author Lian Kok Hai
 */

public class ExitCommand extends Command {
    /**
     * Constructs new ExitCommand with isExit field = True.
     */
    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.getExitMessage();
    }
}
