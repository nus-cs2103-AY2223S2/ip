package botanic.command;

import botanic.exception.BotanicException;
import botanic.storage.Storage;
import botanic.task.TaskList;
import botanic.gui.Gui;

/**
 * Encapsulates the related fields and behavior of the command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Instantiates DeleteCommand object.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task at the given index.
     *
     * @param tasks The ArrayList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @param gui The class that handles interaction with the users.
     * @return A string message to signify the success or failure of task executed.
     * @throws BotanicException if given index is out of range.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Gui gui) throws BotanicException {
        return tasks.delete(this.index);
    }
}
