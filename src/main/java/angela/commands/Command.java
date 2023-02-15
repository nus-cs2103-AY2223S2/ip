package angela.commands;

import angela.exception.AngelaException;
import angela.storage.Storage;
import angela.task.TaskList;
import angela.uitext.UiText;

/**
 * Represents a command that can be executed by Angela.
 */
public abstract class Command {
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command.
     * @param taskList The task list to be used.
     * @param uiText The ui to be used.
     * @param storage The storage to be used.
     * @return The string to be printed.
     * @throws AngelaException If the command cannot be executed correctly.
     */
    public abstract String execute(TaskList taskList, UiText uiText, Storage storage) throws AngelaException;
}
