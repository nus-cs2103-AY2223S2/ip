package commands;

import storage.Storage;
import storage.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * This class is to delete a task at a specified index
 */
public class DeleteCommand extends Command {
    /** Index of task to delete */
    private int index;

    /**
     * Constructs command to delete task at specified index
     *
     * @param i index from user input
     */
    public DeleteCommand(int i) {
        this.index = i;
    }

    /**
     * Deletes task of specified index in taskList
     *
     * @param taskList the list of tasks to delete from
     * @param ui {@inheritDoc}
     * @param storage {@inheritDoc}
     * @return {@inheritDoc}
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task t = taskList.get(index);
        taskList.remove(t);

        return "The following task has been removed: \n    "
                + t + "\n" + taskList.getSizeAsString();
    }
}
