package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    public Command() {}

    public abstract boolean isExit();

    public abstract  void execute(TaskList taskList, Ui ui, Storage storage);
}
