package duke.command;

import duke.main.Storage;
import duke.main.Tasklist;
import duke.main.Ui;

import java.io.IOException;

public abstract class Command {
    private boolean exitCondition = false;
    public abstract void execute(Tasklist taskList, Ui ui, Storage storage) throws IOException;
    public void switchExitCondition() {
        this.exitCondition = !(this.exitCondition);
    }
    public boolean isExit() {
        return this.exitCondition;
    }
}