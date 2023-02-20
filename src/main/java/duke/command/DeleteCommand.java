package duke.command;

import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Deletes task from task list.
 */
public class DeleteCommand extends Command {
    private int taskNum;

    /**
     * Main constructor.
     *
     * @param taskNum number of the task to be deleted.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Deletes task from the task list.
     * Asks UI to print the output.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNum < 1 || taskNum >= tasks.size() + 1) {
            throw new DukeException("\u2639 OOPS!!! The index to mark as done cannot be less than 0 or "
                    + "greater than the length of the list.");
        }

        Task deletedTask = tasks.get(taskNum - 1);
        tasks.delete(taskNum - 1);
        Ui.showDeleteMessage(deletedTask, tasks.size());
        storage.save(tasks.getAllTasks());
    }
}