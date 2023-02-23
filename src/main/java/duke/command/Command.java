package duke.command;

import duke.storage.TaskList;
import duke.ui.Ui;

public abstract class Command {



    public abstract String execute(TaskList list, Ui ui);

    public abstract boolean isExit();

}
