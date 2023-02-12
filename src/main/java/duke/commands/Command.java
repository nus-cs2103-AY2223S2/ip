package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.uitext.UiText;

/**
 * Represents a command that can be executed by Duke.
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
     * @throws DukeException If the command cannot be executed correctly.
     */
    public abstract String execute(TaskList taskList, UiText uiText, Storage storage) throws DukeException;
}
