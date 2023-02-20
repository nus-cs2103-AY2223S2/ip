package botanic.command;

import botanic.gui.Gui;
import botanic.storage.Storage;
import botanic.task.Task;
import botanic.task.TaskList;

/**
 * Encapsulates the related fields and behavior of a command to add a task.
 */
public class AddCommand extends Command {
    /** The task to be added. */
    private Task task;

    /**
     * Instantiates AddCommand.
     *
     * @param task The task to be added into the list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the given task into the taskList.
     *
     * @param tasks The TaskList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @param gui The class that handles interaction with the users.
     * @return A string message signifying Botanic's response to a successful add.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Gui gui) {
        return tasks.add(task);
    }
}
