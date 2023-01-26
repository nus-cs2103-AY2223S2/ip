package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public abstract class Command {
    boolean isExit = false;
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return this.isExit;
    }
}
