package genie.command;

import genie.main.Storage;
import genie.main.TaskList;
import genie.main.Ui;

import java.io.IOException;

public class ExitCommand extends Command {
    public ExitCommand() {}
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.showFarewellMessage();
        storage.saveListToFile(taskList.getTasks());
        storage.closeFileWriter();
    }
    @Override
    public boolean isExitCommand() {
        return true;
    }
}
