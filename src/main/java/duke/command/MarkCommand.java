package duke.command;

import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private int index;
    private boolean exit;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        exit = false;
        return exit;
    }
    
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) {
        tasklist.markItem(index);
    }
}