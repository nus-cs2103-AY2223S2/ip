package red.command;

import red.exception.RedException;
import red.storage.Storage;
import red.task.Task;
import red.task.TaskList;
import red.ui.UI;

public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructor of MarkAsDoneCommand that takes in the index of the task to marked.
     *
     * @param taskIndex The index of the task to be marked
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks the list with the given index as done.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     * @param ui The ui Object used to display information
     * @param storage The Storage Object used to save and load the TaskList
     * @throws RedException Throws exception if the list is empty
     *     or the given index is our of range
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws RedException {

        if (tasks.getTaskListSize() <= 0 || tasks.getTaskListSize() < this.taskIndex) {
            throw new RedException("Task specified does not exist");
        } else {
            Task currentTask = tasks.indexOf(this.taskIndex);
            currentTask.mark();
        }
    }

}
