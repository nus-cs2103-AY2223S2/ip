package duke.command;

import duke.storage.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;


/**
 * The MarkCommand class encapsulates the variables and methods related to Mark commands.
 */
public class MarkCommand extends Command {
    private static final String MARK_COMMAND = "mark";
    private final int index;

    /**
     * Constructor creates an instance of MarkCommand.
     * @param index int index of task to marked as completed.
     */
    public MarkCommand(int index) {
        super(MARK_COMMAND);
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.mark(this.index);
        return ui.showMarkedTask(taskList.getTask(this.index));
    }
}
