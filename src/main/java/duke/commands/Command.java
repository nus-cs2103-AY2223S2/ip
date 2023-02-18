package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Represents an executable command.
 */
public abstract class Command {

    public abstract boolean isExit();
    public abstract String execute(TaskList taskList, Storage storage, TextUi ui);
}
