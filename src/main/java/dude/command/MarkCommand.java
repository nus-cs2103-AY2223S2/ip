package dude.command;

import dude.exception.DudeException;
import dude.storage.Storage;
import dude.task.Task;
import dude.task.TaskList;
import dude.ui.Ui;

/**
 * Command to mark Task.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Initializes MarkCommand.
     *
     * @param taskIndex Task index that has to be marked in TaskList.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (Task.getTaskCount() < taskIndex || Task.getTaskCount() == 0) {
            return ui.showError("Uhh... Where got this task for me to mark?");
        }
        try {
            tasks.markTask(taskIndex);
            Task currentTask = tasks.getTask(taskIndex);
            storage.saveData(tasks);
            return ui.showMark(currentTask);
        } catch (DudeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
