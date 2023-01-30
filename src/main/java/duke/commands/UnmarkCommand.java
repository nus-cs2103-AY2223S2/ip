package duke.commands;

import duke.DukeException;
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
     * Executes the command.
     *
     * @param taskList Respective task list.
     * @param ui Respective Ui.
     * @param storage Respective storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task t = taskList.markTaskInListUndone(super.getTargetIndex());
        ui.unmarkResponse(t);
        storage.deleteFileAndRedo(taskList.getList());
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
