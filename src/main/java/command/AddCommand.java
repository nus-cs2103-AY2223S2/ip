package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Command for adding a task to a task list.
 */
public class AddCommand extends Command {
    private final char taskType;
    private final String content;

    /**
<<<<<<< HEAD
     * Constructs an AddCommand object.
     * @param taskType The type of task to create in the command.
     * @param commandContent Content to be stored within the task.
=======
     * Constructor for AddCommand.
     * @param taskType The type of task to add to the task list.
     * @param commandContent The content to be added to the task.
>>>>>>> master
     */
    public AddCommand(char taskType, String commandContent) {
        this.taskType = taskType;
        this.content = commandContent;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = Task.create(this.taskType, this.content);
        taskList.addTask(task);
    }
}
