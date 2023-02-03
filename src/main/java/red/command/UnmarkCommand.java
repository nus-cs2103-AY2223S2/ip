package red.command;

import red.exception.RedException;

import red.storage.Storage;

import red.task.Task;
import red.task.TaskList;

import red.ui.UI;

/**
 * This class specifies the marking of a task in the current list of tasks as not completed.
 */


public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * The constructor of UnmarkCommand that takes in the index of the task to be unmarked.
     *
     * @param taskIndex The index of the task to be marked
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks the task in the current list of tasks with the given index as not done.
     *
     * @param tasks The TaskList that contains the current list of tasks.
     * @param ui The UI that interprets any user inputs.
     * @param storage The Storage that keeps track of how the list of tasks changes from user input.
     * @throws RedException Throws Exception when the user inputs invalid instruction.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws RedException {
        if (tasks.getTaskListSize() <= 0 || tasks.getTaskListSize() < taskIndex) {
            throw new RedException("Task specified does not exist");
        } else {
            Task currentTask = tasks.indexOf(this.taskIndex);
            ui.addCurrentReply(currentTask.unmark());
        }
    }
}
