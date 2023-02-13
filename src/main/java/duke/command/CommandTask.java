package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command to add a task.
 */
public class CommandTask extends Command {
    private final Task task;

    /**
     * Returns a task command.
     * @param command full unparsed command.
     * @param task task to be added.
     */
    public CommandTask(String command, Task task) {
        super(command);
        this.task = task;
    }

    /**
     * Adds task to task list.
     * @param taskList contains the task list.
     * @param ui deals with interactions with the user.
     * @param storage deals with loading and saving tasks from file.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        String addedString = ("added: " + task.toString() + "\n");
        String len = (taskList.getLength() == 1 ? taskList.getLength() + " task" : taskList.getLength() + " tasks");
        String lengthString = ("Now you have " + len + " in the list.");

        storage.writeArray(taskList);
        return addedString + lengthString;
    }
}
