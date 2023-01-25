package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public abstract class Command {
    protected String[] command;
    
    public Command(String[] command) {
        this.command = command;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    public boolean isExit() {
        return false;
    }
}
