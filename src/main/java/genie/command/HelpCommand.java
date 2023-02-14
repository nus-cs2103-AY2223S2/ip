package genie.command;

import genie.main.Storage;
import genie.main.TaskList;
import genie.main.Ui;

public class HelpCommand extends Command {
    public HelpCommand() {}
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.appendHelpMessage();
    }
    @Override
    public boolean isExitCommand() {
        return false;
    }
}
