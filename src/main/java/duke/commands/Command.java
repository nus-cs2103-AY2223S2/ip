package duke.commands;

import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;

public abstract class Command {

    public abstract void execute(Tasks tasks, Ui ui, Storage storage);
}
