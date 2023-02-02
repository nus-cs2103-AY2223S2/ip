package duke.command;

import duke.Ui;

public abstract class Command {
    public abstract void handleCommand(Ui ui);
    public abstract boolean isExit();
}
