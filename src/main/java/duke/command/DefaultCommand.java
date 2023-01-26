package duke.command;

import tasklist.TaskList;
import exceptions.TaskException;
import storage.Storage;
import ui.Ui;

public class DefaultCommand extends Command{
    private boolean exit;

    @Override
    public boolean isExit() {
        exit = false;
        return exit;
    }
    
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) throws TaskException {
        ui.error("default");
    }
}
