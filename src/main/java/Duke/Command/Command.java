package Duke.Command;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public abstract class Command {

    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList l, Ui ui, Storage s) throws Exception;
}
