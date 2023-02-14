package genie.command;

import genie.Storage;
import genie.TaskList;
import genie.Ui;
import genie.task.Task;

public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task t = taskList.getTasks().get(index - 1);
        taskList.deleteTask(index - 1);
        int taskListSize = taskList.getTasks().size();
        ui.showDeleteTaskMessage(t, taskListSize);
    }
    @Override
    public boolean isExitCommand() {
        return false;
    }
}
