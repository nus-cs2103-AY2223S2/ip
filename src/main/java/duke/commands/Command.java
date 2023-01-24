package duke.commands;

import duke.taskType.TaskList;
import duke.Ui;
import duke.Storage;

public abstract class Command {
    public abstract void operate(TaskList lst, Ui ui, Storage storage);

    public boolean isBye() {
        return false;
    }
}
