package command;

import gui.Gui;
import storage.Storage;
import task.Task;
import task.TaskList;

/**
 * Command for adding a task to a task list.
 */
public class AddCommand extends Command {
    private final char taskType;
    private final String content;

    /**
     * Constructs an AddCommand object.
     * @param commandType The type of task to create in the command.
     * @param commandContent Content to be stored within the task.
     */
    public AddCommand(CommandType commandType, String commandContent) {
        this.taskType = commandType.toString().charAt(0);
        this.content = commandContent;
    }

    @Override
    public void execute(TaskList taskList, Gui gui, Storage storage) {
        Task task = Task.create(this.taskType, this.content);
        gui.say(taskList.addTask(task));
    }
}
