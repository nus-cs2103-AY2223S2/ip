package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public abstract class Command {
    private boolean isExit = false;
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return this.isExit;
    }

    public void setExit() {
        this.isExit = true;
    }
}
