package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;
/**
 * DeleteCommand is a command that deletes a task from the task list
 */

public class DeleteCommand extends Command {
    private int index;
    private boolean isFind;

    /**
     * A Constructor for DeleteCommand.
     *
     * @param index Index of task to be deleted from task list.
     */
    public DeleteCommand(int index, boolean isFind) {
        super(false);
        this.index = index;
        this.isFind = isFind;
    }

    /**
     * A method that deletes a task from the taskList or findList depending on boolean isFind.
     *
     * @param tasks Task list containing the list of tasks.
     * @param storage Saves tasks into the file locally.
     * @param ui Deals with interactions with user.
     * @return String response from Duke.
     * @throws DukeException if command cannot be recognised or task number does not exist.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("\tYou currently have no tasks in your list to delete.");
        } else {
            try {
                if (isFind) {
                    ArrayList<Task> listOfFindTask = tasks.getListOfFindTasks();
                    Task temp = listOfFindTask.get(index);
                    listOfFindTask.remove(index);
                    String s = ui.showFindDelete(temp);
                    tasks.getListOfTasks().remove(temp);
                    return s;
                } else {
                    Task deletedTask = tasks.deleteTask(index);
                    int size = tasks.getSize();
                    String s = ui.showDelete(deletedTask, size);
                    storage.saveTasksToFile(tasks.getListOfTasks());
                    return s;
                }
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("\tTask number does not exist!");
            }
        }
    }
}
