package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;

public class ByeCommand implements Command{
    private String input;

    public ByeCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.exitMessage();
    }
}
