package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;
import tasks.Task;

public class MarkCommand implements Command{
    private String input;
    public MarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTaskList().get(getIndex() - 1);
        task.markAsDone();
        storage.store(taskList.getTaskList());
        ui.markMessage(task);
    }

    public Integer getIndex() {
        return Integer.valueOf(input.split(" ")[1]);
    }
}
