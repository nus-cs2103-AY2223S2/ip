package angela.commands;

import angela.storage.Storage;
import angela.task.TaskList;
import angela.uitext.UiText;

/**
 * Represents a command to exit Angela.
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
