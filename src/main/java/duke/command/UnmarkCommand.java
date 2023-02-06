package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Unmarks task when user input indicates unmark.
 */
public class UnmarkCommand extends Command {
    private final String[] TASK_INDEXES;

    public UnmarkCommand(String ... taskIndexes) {
        this.TASK_INDEXES = taskIndexes;
    }

    /**
     * Unmarks task and updates the task list in the local storage.
     *
     * @param tasks List of tasks.
     * @param ui Ui object that handles all Ui actions.
     * @param storage Storage object that handles all Storage actions.
     * @throws DukeException Throws exception from the methods called in this method.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String taskString = "";
        for (String taskIndex: TASK_INDEXES) {
            taskString = String.format("%s%s\n", taskString,
                    tasks.unmarkTask(Integer.parseInt(taskIndex) - 1));
        }
        storage.saveTasks(tasks);
        return ui.getUnmarkOutput(taskString);
    }
}
