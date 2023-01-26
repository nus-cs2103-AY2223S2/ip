package duke.command;

import duke.Storage;
import duke.Ui;
import duke.TaskList;

public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
