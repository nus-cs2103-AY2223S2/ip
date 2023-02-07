package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    public boolean isExit() {
        return false;
    }

    public abstract String execute(TaskList l, Ui ui, Storage s) throws Exception;
}
