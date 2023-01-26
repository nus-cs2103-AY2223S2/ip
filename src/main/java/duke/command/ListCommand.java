package duke.command;

import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ListCommand extends Command {
    
    private boolean exit;

    @Override
    public boolean isExit() {
        exit = false;
        return exit;
    }

    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) {
        tasklist.printList();
    }
}
