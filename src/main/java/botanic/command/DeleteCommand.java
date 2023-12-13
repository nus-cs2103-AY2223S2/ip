package botanic.command;

import botanic.exception.BotanicException;
import botanic.gui.Gui;
import botanic.storage.Storage;
import botanic.task.TaskList;

/**
 * Encapsulates the related fields and behavior of the command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Instantiates DeleteCommand.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task at the given index.
     *
     * @param tasks The TaskList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @param gui The class that handles interaction with the users.
     * @return A string message signifying Botanic's response to a successful delete.
     * @throws BotanicException If given index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Gui gui) throws BotanicException {
        return tasks.delete(index);
    }
}
