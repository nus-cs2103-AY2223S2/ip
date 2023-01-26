package duke;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

public abstract class Command {
    public String input;

    public Command(String input) {
        this.input = input;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public boolean isExit() {
        return true;
    }

}
