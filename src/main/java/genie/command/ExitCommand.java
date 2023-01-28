package genie.command;

import genie.Storage;
import genie.TaskList;
import genie.Ui;

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
