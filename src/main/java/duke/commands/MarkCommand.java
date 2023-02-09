package duke.commands;

import duke.exceptions.DukeException;
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
     * Executes the task.
     *
     * @param taskList Respective task list.
     * @param ui Respective Ui.
     * @param storage Respective storage.
     * @return String response.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task t = taskList.markTaskInListDone(super.getTargetIndex());
        storage.deleteFileAndRedo(taskList.getList());
        return ui.markResponse(t);

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
