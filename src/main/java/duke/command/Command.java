package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }
    public boolean isExit()  {
        return this.isExit;
    }

    public abstract void execute(TaskList task, Storage storage, Ui ui) throws DukeException;
}
