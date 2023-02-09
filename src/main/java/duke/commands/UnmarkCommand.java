package duke.commands;

import duke.exceptions.DukeException;
import duke.taskers.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Unmark Command.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    /**
     * Constructor.
     *
     * @param targetIndex Index to be acted on in the task list.
     */
    public UnmarkCommand(int targetIndex) {
        super(targetIndex);
    }

    /**
     * Executes the task.
     *
     * @param taskList Respective task list.
     * @param ui Respective Ui.
     * @param storage Respective storage.
     * @return String response.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task t = taskList.markTaskInListUndone(super.getTargetIndex());
        storage.deleteFileAndRedo(taskList.getList());
        return ui.unmarkResponse(t);

    }

    /**
     * Checks if command exits.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
