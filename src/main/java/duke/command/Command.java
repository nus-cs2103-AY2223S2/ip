package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.Tasklist;
import duke.main.Ui;

import java.io.IOException;

public abstract class Command {
    private boolean shouldExit = false;

    public abstract String execute(Tasklist taskList, Ui ui, Storage storage) throws IOException, DukeException;

    public void switchExitCondition() {
        this.shouldExit = !(this.shouldExit);
    }

    public boolean isExit() {
        return this.shouldExit;
    }
}