package nemo.command;

import nemo.storage.Storage;
import nemo.task.Task;
import nemo.task.TaskList;
import nemo.task.ToDo;
import nemo.ui.Ui;

/**
 * Command to add ToDo Task to TaskList.
 *
 */
public class ToDoCommand extends Command {
    protected String taskName;

    /**
     * Creates new ToDoCommand.
     *
     * @param taskName Name of ToDo Task.
     */
    public ToDoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task newTask = new ToDo(taskName);
        taskList.addTask(newTask);
        storage.saveTaskList(taskList);
        return ui.getAddTaskMessage(newTask, taskList.getCount());
    }
}
