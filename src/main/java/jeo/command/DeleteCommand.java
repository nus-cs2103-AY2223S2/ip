package jeo.command;

import jeo.database.TaskList;
import jeo.task.Task;
import jeo.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(Ui ui, TaskList taskList) {
        Task task = taskList.getTaskAtIndex(index);
        taskList.deleteTask(index);
        return ui.taskDeletedMessage(task, taskList.getNumberOfTasks());
    }

    @Override
    public String toString() {
        return "delete";
    }
}
