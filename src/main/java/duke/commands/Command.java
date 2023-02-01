package duke.commands;
import duke.database.Database;
import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public abstract class Command {

    public boolean isActive;

    private static final String FRAME = "    ____________________________________________________________\n";

    public Command() {
        this.isActive = true;
    }

    public abstract void execute(TaskList taskList, Ui ui, Database database) throws DukeException;

}
