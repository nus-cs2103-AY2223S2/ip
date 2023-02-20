package botanic.command;

import botanic.storage.Storage;
import botanic.task.TaskList;
import botanic.gui.Gui;

/**
 * Encapsulates the related fields and behavior of the command to list out all tasks.
 */
public class ListCommand extends Command {
    /**
     * Prints out all tasks in the list.
     *
     * @param tasks The ArrayList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @param gui The class that handles interaction with the users.
     * @return A string representation of the list of all the tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Gui gui) {
        return tasks.print();
    }
}
