package red.command;

import red.exception.RedException;
import red.storage.Storage;
import red.task.Task;
import red.task.TaskList;
import red.ui.UI;

/**
 * A more specific instruction class that encapsulates the action of adding a task
 * into the given TaskList.
 */

public class AddCommand extends Command {
    private final Task task;

    /**
     * The constructor of AddTaskCommand that takes in the task to be added.
     *
     * @param task The task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the given task to the TaskList and display relevant information with the customized format.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws RedException {
        tasks.addTask(this.task);
    }

    /**
     * Compares this object to the specified object.
     *
     * @param obj the object to compare with
     * @return true if the objects are the same; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AddCommand)) {
            return false;
        }
        AddCommand ddlObj = (AddCommand) obj;

        return this.task.equals(ddlObj.task);
    }

    /**
     * Returns a string representation of the AddTaskCommand in the format "Add Task: task".
     *
     * @return A string representation of the AddTaskCommand
     */
    @Override
    public String toString() {
        return "Add Task: " + this.task;
    }

}
