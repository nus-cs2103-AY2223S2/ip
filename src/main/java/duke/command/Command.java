package duke.command;
import duke.Storage;
import duke.Task;
import duke.Tasklist;
import duke.Ui;

/**
 * Abstract class representing a {@code Command}.
 */
public abstract class Command {

    public abstract String execute(Tasklist tasklist) throws Exception;

    public abstract boolean isExit();
}

