package red.command;

import red.exception.RedException;

import red.storage.Storage;

import red.task.Task;
import red.task.TaskList;

import red.ui.UI;

/**
 * This class specifies the act of adding a class to the current list of tasks.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * The constructor of AddCommand that takes in the task to be added.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the given task to the TaskList.
     *
     * @param tasks The TaskList that contains the current list of tasks.
     * @param ui The UI that interprets any user inputs.
     * @param storage The Storage that keeps track of how the list of tasks changes from user input.
     * @throws RedException Throws Exception when the user inputs invalid instruction.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws RedException {
        boolean isAdded = tasks.addTask(task);
        if(isAdded) {
            ui.addCurrentReply("New Task Added: " + task.getDescription() +"\n");
            ui.addCurrentReply("There are now " + tasks.getTaskListSize() + " task(s) awaiting completion\n");
            return;
        }
        ui.addCurrentReply("Specified Task already exists\n");

    }

    /**
     * Compares this object to the specified object.
     *
     * @param obj the object to compare with.
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
     * Returns a string representation of the AddCommand in the format "Add Task: task".
     *
     * @return A string representation of the AddCommand.
     */
    @Override
    public String toString() {
        return "Add Task: " + this.task;
    }

}
