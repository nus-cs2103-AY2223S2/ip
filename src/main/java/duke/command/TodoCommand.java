package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.task.Task;
import duke.ui.Ui;


public class TodoCommand extends Command {
    protected String taskName;

    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task newTask = new ToDo(taskName);
        taskList.addTask(newTask);
        ui.printAddTaskMessage(newTask, taskList.getCount());
        storage.saveTaskList(taskList);
    }
}
