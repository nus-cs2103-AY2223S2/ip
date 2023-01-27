package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents an executable command.
 */
public abstract class Command {

    public abstract boolean isExit();
    public abstract void execute(TaskList taskList, Storage storage);
}
