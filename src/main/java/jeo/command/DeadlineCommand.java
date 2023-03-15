package jeo.command;

import jeo.database.TaskList;
import jeo.task.Deadline;
import jeo.ui.Ui;

public class DeadlineCommand extends Command {
    private Deadline task;

    public DeadlineCommand(Deadline task) {
        this.task = task;
    }

    @Override
    public String execute(Ui ui, TaskList taskList) {
        taskList.addTask(task);
        return ui.taskAddedMessage(task, taskList.getNumberOfTasks());
    }

    @Override
    public String toString() {
        return "deadline";
    }
}
