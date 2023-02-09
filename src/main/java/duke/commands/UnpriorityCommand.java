package duke.commands;

import duke.exceptions.DukeException;
import duke.taskers.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Delete Command.
 */
public class UnpriorityCommand extends Command {

    public static final String COMMAND_WORD = "unprioritise";

    /**
     * Constructor.
     * @param targetIndex Index of the task list to be called upon.
     */
    public UnpriorityCommand(int targetIndex) {
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
        Task t = taskList.unprioritiseTask(super.getTargetIndex());
        storage.deleteFileAndRedo(taskList.getList());
        return ui.unprioritiseResponse(t);

    }

    /**
     * Checks if command exits or not.
     * @return Supposed to return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

