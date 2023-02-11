package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;

public class ListCommand implements Command {
    private String input;

    public ListCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printList(taskList);
    }
}
