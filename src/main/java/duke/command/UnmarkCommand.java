package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Unmarks task when user input indicates unmark.
 */
public class UnmarkCommand extends Command {
    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Unmarks task and updates the task list in the local storage.
     *
     * @param tasks List of tasks.
     * @param ui Ui object that handles all Ui actions.
     * @param storage Storage object that handles all Storage actions.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String taskString = tasks.unmarkTask(this.idx - 1);
        storage.saveTasks(tasks);
        ui.showUnmark(taskString, tasks);
    }
}
