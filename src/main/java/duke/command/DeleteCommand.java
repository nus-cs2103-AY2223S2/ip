package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * DeleteCommand is a command that deletes a task from the task list
 */

public class DeleteCommand extends Command {
    int index;

    /**
     * Constructor for DeleteCommand.
     *
     * @param index Index of task to be deleted from task list.
     */
    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * A method that deletes a task from the task list.
     *
     * @param task Task list containing the list of tasks.
     * @param storage Saves tasks into the file locally.
     * @param ui Deals with interactions with user.
     * @return String response from Duke.
     * @throws DukeException if command cannot be recognised or task number does not exist.
     */
    @Override
    public String execute(TaskList task, Storage storage, Ui ui) throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException("\tYou currently have no tasks in your list to delete.");
        } else {
            try {
                Task deletedTask = task.deleteTask(index);
                int size = task.getSize();
                String s = ui.showDelete(deletedTask, size);
                storage.saveTasksToFile(task.getListOfTasks());
                return s;
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("\tTask number does not exist!");
            }
        }
    }
}
