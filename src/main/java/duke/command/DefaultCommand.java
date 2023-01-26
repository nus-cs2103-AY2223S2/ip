package duke.command;

import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.exceptions.TaskException;
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
