package duke.commands;

import duke.data.MyData;
import duke.ui.Ui;

public abstract class Command {
    public abstract void execute(MyData data, Ui ui);
}
