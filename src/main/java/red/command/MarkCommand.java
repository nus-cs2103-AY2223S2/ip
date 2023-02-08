package red.command;

import red.exception.RedException;

import red.storage.Storage;

import red.task.Task;
import red.task.TaskList;

import red.ui.UI;

/**
 * This class specifies the marking of a task in the current list of tasks as completed.
 */

public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * The constructor of MarkCommand that takes in the index of the task to be marked.
     *
     * @param taskIndex The index of the task to be marked
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks the task in the current list of tasks with the given index as done.
     *
     * @param tasks The TaskList that contains the current list of tasks.
     * @param ui The UI that interprets any user inputs.
     * @param storage The Storage that keeps track of how the list of tasks changes from user input.
     * @throws RedException Throws Exception when the user inputs invalid instruction.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws RedException {
        int taskListSize = tasks.getTaskListSize();
        assert taskListSize > -1;
        boolean isEqualToZero =  taskListSize == 0;
        boolean isLessThanIndex = taskListSize < taskIndex;
        boolean isInvalidIndex = isEqualToZero || isLessThanIndex;

        if (isInvalidIndex) {
            throw new RedException("Task specified does not exist");
        } else {
            Task currentTask = tasks.indexOf(this.taskIndex);
            ui.addCurrentReply(currentTask.mark());
        }
    }

}
