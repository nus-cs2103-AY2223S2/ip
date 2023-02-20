package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;

public class HelpCommand implements Command {

    private String input;

    public HelpCommand(String input) {
        this.input = input;
    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showHelp();
    }
}
