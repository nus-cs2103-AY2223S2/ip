package command;

import tasklist.TaskList;
import storage.Storage;
import ui.Ui;

public class ByeCommand extends Command {
    
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) {
        ui.byeMessage();
        storage.writeToFile();
    }
}
