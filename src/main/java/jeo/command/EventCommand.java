package jeo.command;

import jeo.database.TaskList;
import jeo.task.Event;
import jeo.ui.Ui;

public class EventCommand extends Command {
    private Event task;

    public EventCommand(Event task) {
        this.task = task;
    }

    @Override
    public String execute(Ui ui, TaskList taskList) {
        taskList.addTask(task);
        return ui.taskAddedMessage(task, taskList.getNumberOfTasks());
    }

    @Override
    public String toString() {
        return "event";
    }
}
