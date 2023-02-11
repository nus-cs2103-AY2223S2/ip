package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;

public class DeleteCommand implements Command {
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.getTaskList().remove(getIndex() - 1);
        storage.store(taskList.getTaskList());
    }

    public Integer getIndex() {
        return Integer.valueOf(input.split(" ")[1]);
    }
}
