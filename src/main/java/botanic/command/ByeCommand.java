package botanic.command;

import botanic.gui.Gui;
import botanic.storage.Storage;
import botanic.task.Task;
import botanic.task.TaskList;
import botanic.task.ToDo;

/**
 * Encapsulates the related fields and behavior of the command to exit the program.
 */
public class ByeCommand extends Command {
    /**
     * Prints out goodbye message.
     *
     * @param tasks The TaskList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @param gui The class that handles interaction with the users.
     * @return The goodbye message string.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Gui gui) {
        Task[] tempTasks = { new ToDo("a") };
        storage.writeToFile(tasks.getTaskList().toArray(tempTasks));
        return gui.getBye();
    }
}
