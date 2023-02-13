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
     * Alternate constructor for undo commands.
     * @param task task to be added.
     */
    public CommandTask(Task task) {
        super("");
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

    /**
     * Returns a delete command to delete a command.
     * @param taskList contains the task list.
     * @return task command with the deleted task.
     */
    @Override
    public Command inverseCommand(TaskList taskList) {
        int index = taskList.getLength() - 1;
        return new CommandDelete(index);
    }
}
