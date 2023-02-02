package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;



/**
 * A Command is an object that can be executed to interact with duke.
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
