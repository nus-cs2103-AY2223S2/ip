package dude.command;

import dude.storage.Storage;
import dude.task.Task;
import dude.task.TaskList;
import dude.ui.Ui;

/**
 * Command to delete Task
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Initializes DeleteCommand.
     *
     * @param taskIndex Task index that has to be deleted from TaskList.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (Task.getTaskCount() >= taskIndex && Task.getTaskCount() != 0) {
            Task currentTask = tasks.getTask(taskIndex);
            tasks.deleteTask(taskIndex);
            storage.saveData(tasks);
            return ui.showDelete(currentTask);
        } else {
            return ui.showError("Uhh... Where got this task for me to delete?");
        }
    }
}
