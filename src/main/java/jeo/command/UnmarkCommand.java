package jeo.command;

import jeo.database.TaskList;
import jeo.task.Task;
import jeo.ui.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(Ui ui, TaskList taskList) {
        Task task = taskList.getTaskAtIndex(index);
        taskList.unmarkTask(index);
        return ui.taskUnmarkedMessage(task);
    }

    @Override
    public String toString() {
        return "unmark";
    }
}
