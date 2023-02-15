package botanic.command;

import botanic.exception.BotanicException;
import botanic.storage.Storage;
import botanic.task.TaskList;
import botanic.ui.Ui;

/**
 * Encapsulates the related fields and behavior of the command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index;
    /**
     * Instantiates MarkCommand.
     *
     * @param index The index of the task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param tasks The ArrayList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @param ui The class that handles interaction with the users.
     * @return A string message to signify the success or failure of task executed.
     * @throws BotanicException If given index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws BotanicException {
        return tasks.markIsDone(this.index);
    }
}
