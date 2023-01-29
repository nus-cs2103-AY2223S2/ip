package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public Command() {}

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException;

    public boolean isExit() {
        return false;
    }
}
