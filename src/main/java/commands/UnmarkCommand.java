package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;
import tasks.Task;

public class UnmarkCommand implements Command {
    private String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTaskList().get(getIndex(input) - 1);
        task.markAsNotDone();
        storage.store(taskList.getTaskList());
        ui.unmarkMessage(task);
    }

    public Integer getIndex(String string) {
        return Integer.valueOf(string.split(" ")[1]);
    }
}
