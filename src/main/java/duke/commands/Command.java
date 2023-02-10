package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UserInterface;

/**
 * Represents a command that can be executed.
 * 
 * @author Samarth Verma
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param list The task list.
     * @param ui The user interface.
     * @param storage The storage.
     * @throws Exception If an error occurs while executing the command.
     */
    public abstract void execute(TaskList list, UserInterface ui, Storage storage) throws Exception;
}
