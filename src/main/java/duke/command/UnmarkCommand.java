package duke.command;

import duke.exception.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * The UnmarkCommand class encapsulates the variables and methods related to Unmark commands.
 */
public class UnmarkCommand extends Command {
    public static final String UNMARK_COMMAND = "unmark";
    private final int index;

    public UnmarkCommand(int index) {
        super(UNMARK_COMMAND);
        this.index = index;
    }

    @Override
    public String execute(TaskList lst, Ui ui) throws DukeException {
        lst.unmark(this.index);
        return ui.showUnmarkedTask(lst.getTask(this.index));
    }
}
