package red.command;

import red.exception.RedException;

import red.storage.Storage;

import red.task.Task;
import red.task.TaskList;

import red.ui.UI;

/**
 * This class specifies the act of deleting a class from the current list of tasks.
 */

public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * The constructor for DeleteCommand that takes in the index of the task to be deleted.
     *
     * @param taskIndex the index of the task to be deleted
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Remove the specified task(by index) from the current list of tasks.
     *
     * @param tasks The TaskList that contains the current list of tasks.
     * @param ui The UI that interprets any user inputs.
     * @param storage The Storage that keeps track of how the list of tasks changes from user input.
     * @throws RedException Throws Exception when the user inputs invalid instruction.
     *
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws RedException {
        boolean isTaskListEmpty = tasks.getTaskListSize() < 1;
        boolean isIndexOutOfRange = tasks.getTaskListSize() < this.taskIndex;

        if (isTaskListEmpty) {
            throw new RedException("There are no tasks to be removed");
        } else if (isIndexOutOfRange) {
            throw new RedException("The task with the specified index does not exist");
        } else {
            Task deletedTask = tasks.deleteTask(this.taskIndex);
            String message = String.format("Noted. I've removed this task:\n %s \nNow you have %d tasks in the list.",
                    deletedTask.toString(), tasks.getTaskListSize());
            ui.addCurrentReply(message);
            System.out.println(message);
        }
    }

}