package duke.commands;

import duke.data.MyData;
import duke.ui.Ui;

public abstract class Command {
    /**
     * Executes the command.
     *
     * @param data Data storing the tasks.
     * @param ui Ui object which handles interaction with user.
     */
    public abstract void execute(MyData data, Ui ui);
}
