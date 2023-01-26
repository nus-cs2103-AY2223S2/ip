package duke.command;

import tasklist.TaskList;
import storage.Storage;
import ui.Ui;

public class ByeCommand extends Command {

    private boolean exit;

    @Override
    public boolean isExit() {
        exit = true;
        return exit;
    }
    
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) {
        ui.byeMessage();
        storage.writeToFile();
    }
}
