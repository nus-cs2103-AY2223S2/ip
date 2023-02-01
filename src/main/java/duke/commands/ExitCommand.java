package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.uitext.UiText;

/**
 * Represents a command to exit Duke.
 */
public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList taskList, UiText uiText, Storage storage) {
        return null;
    }
}
