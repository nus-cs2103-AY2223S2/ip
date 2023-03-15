package jeo.command;

import jeo.database.TaskList;
import jeo.task.ToDo;
import jeo.ui.Ui;

public class TodoCommand extends Command {
    private ToDo task;

    public TodoCommand(ToDo task) {
        this.task = task;
    }

    @Override
    public String execute(Ui ui, TaskList taskList) {
        taskList.addTask(task);
        return ui.taskAddedMessage(task, taskList.getNumberOfTasks());
    }

    @Override
    public String toString() {
        return "todo";
    }
}
