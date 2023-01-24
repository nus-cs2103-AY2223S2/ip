package dude.command;

import dude.task.Task;
import dude.task.TaskList;
import dude.storage.Storage;
import dude.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (Task.count >= taskIndex && Task.count != 0) {
            Task currentTask = tasks.getTask(taskIndex);
            currentTask.unmark();
            storage.saveData(tasks);
            ui.showUnmark(currentTask);
        } else {
            ui.showError("\tUhh... Where got this task for me to mark?");
        }
    }
}
