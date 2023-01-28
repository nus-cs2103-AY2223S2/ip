package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.List;
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
