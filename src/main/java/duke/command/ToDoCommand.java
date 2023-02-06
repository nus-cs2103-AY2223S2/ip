package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

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
