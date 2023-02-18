package botanic.command;

import botanic.exception.BotanicException;
import botanic.gui.Gui;
import botanic.storage.Storage;
import botanic.task.TaskList;

/**
 * Encapsulates the related fields and behavior of the command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Instantiates UnmarkCommand.
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param tasks The ArrayList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @param gui The class that handles interaction with the users.
     * @return A string message to signify the success or failure of task executed.
     * @throws BotanicException if given index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Gui gui) throws BotanicException {
        return tasks.unmarkIsDone(this.index);
    }
}
