package botanic.command;

import botanic.exception.BotanicException;
import botanic.gui.Gui;
import botanic.storage.Storage;
import botanic.task.TaskList;

/**
 * Encapsulates the related fields and behavior of a command.
 */
public abstract class Command {
    /**
     * Executes the command given.
     *
     * @param tasks The ArrayList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @param gui The class that handles interaction with the users.
     * @return A string message to signify the success or failure of task executed.
     * @throws BotanicException If task is not executed successfully.
     */
    public abstract String execute(TaskList tasks, Storage storage, Gui gui) throws BotanicException;

    /**
     * Returns whether the command requires the program to exit.
     *
     * @return True if the program should exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    };
}
