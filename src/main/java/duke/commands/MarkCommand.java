package duke.commands;

import duke.DukeException;
import duke.taskers.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Mark Command.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    /**
     * Constructor.
     *
     * @param targetIndex Index to be acted upon in the task list.
     */
    public MarkCommand(int targetIndex) {
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
        Task t = taskList.markTaskInListDone(super.getTargetIndex());
        ui.markResponse(t);
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
