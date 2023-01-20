package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui inter, Storage store);

    public abstract boolean isExit();
}
