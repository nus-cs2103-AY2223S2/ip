package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The DeleteCommand class implements the action of deleting tasks.
 *
 * @author Chia Jeremy
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Class constructor for the delete command.
     *
     * @param index the index of the task to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command.
     *
     * @param storage the file to save the tasks
     * @param tasks   the task lists
     * @param ui      the interface that deals with interactions with the user
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        Task task = getDeleteTask(tasks);
        deleteFromTaskList(tasks);
        deleteFromStorage(storage);
        setUiResponse(task, tasks, ui);
    }

    private Task getDeleteTask(TaskList tasks) {
        return tasks.getTask(this.index);
    }

    private void deleteFromTaskList(TaskList tasks) {
        tasks.delete(this.index);
    }

    private void deleteFromStorage(Storage storage) {
        storage.delete(this.index);
    }

    private void setUiResponse(Task task, TaskList tasks, Ui ui) {
        ui.setResponse("Noted. I've removed this task:\n"
                + task + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
