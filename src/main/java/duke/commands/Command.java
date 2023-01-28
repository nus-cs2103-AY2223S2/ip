package duke.commands;

import duke.duke.Ui;
import duke.storage.Storage;
import duke.storage.TaskList;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    public boolean isBye() {
        return false;
    }
}
