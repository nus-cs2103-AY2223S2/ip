package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Marks task when user input indicates mark.
 */
public class MarkCommand extends Command {
    private final String[] TASK_INDEXES;

    public MarkCommand(String ... taskIndexes) {
        this.TASK_INDEXES = taskIndexes;
    }

    /**
     * Marks task and updates the task list in the local storage.
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
                    tasks.markTask(Integer.parseInt(taskIndex) - 1));
        }
        storage.saveTasks(tasks);
        return ui.getMarkOutput(taskString);
    }
}
