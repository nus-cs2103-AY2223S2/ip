package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to terminate Duke application.
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
