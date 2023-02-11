package duke.command;

import duke.exception.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * The MarkCommand class encapsulates the variables and methods related to Mark commands.
 */
public class MarkCommand extends Command {
    public static final String MARK_COMMAND = "mark";
    private final int index;

    public MarkCommand(int index) {
        super(MARK_COMMAND);
        this.index = index;
    }

    @Override
    public String execute(TaskList lst, Ui ui) throws DukeException {
        lst.mark(this.index);
        return ui.showMarkedTask(lst.getTask(this.index));
    }
}
