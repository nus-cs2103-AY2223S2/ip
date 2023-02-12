package duke.command;

import duke.storage.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;

/**
 * The UnmarkCommand class encapsulates the variables and methods related to Unmark commands.
 */
public class UnmarkCommand extends Command {
    private static final String UNMARK_COMMAND = "unmark";
    private final int index;

    /**
     * Constructor creates an instance of UnmarkCommand
     * @param index int index of task to be mark an incomplete.
     */
    public UnmarkCommand(int index) {
        super(UNMARK_COMMAND);
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.unmark(this.index);
        return ui.showUnmarkedTask(taskList.getTask(this.index));
    }
}
