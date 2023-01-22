package duke.command;
import duke.Ui.Ui;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Abstract class containing 2 methods for the command objects to implement
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;


    public abstract boolean isExit();
}
