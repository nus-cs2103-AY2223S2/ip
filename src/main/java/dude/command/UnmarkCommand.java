package dude.command;

import dude.exception.DudeException;
import dude.storage.Storage;
import dude.task.Task;
import dude.task.TaskList;
import dude.ui.Ui;

/**
 * Command to unmark Task.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Initializes UnmarkCommand.
     *
     * @param taskIndex Task index that has to be unmarked in TaskList.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (Task.getTaskCount() < taskIndex || Task.getTaskCount() == 0) {
            return ui.showError("Uhh... Where got this task for me to unmark?");
        }
        try {
            tasks.unmarkTask(taskIndex);
            Task currentTask = tasks.getTask(taskIndex);
            storage.saveData(tasks);
            return ui.showMark(currentTask);
        } catch (DudeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
