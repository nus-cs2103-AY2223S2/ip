package duke.command;

import duke.exception.StorageFileException;
import duke.storage.CommandHistory;
import duke.storage.Storage;
import duke.task.DukeTask;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A more specific instruction class that encapsulates the action of adding a task
 * into the given TaskList.
 */

public class AddTaskCommand extends Command {
    private final DukeTask task;

    /**
     * The constructor of AddTaskCommand that takes in the task to be added.
     *
     * @param task The task to be added
     */
    public AddTaskCommand(DukeTask task) {
        this.task = task;
    }

    /**
     * Adds the given task to the TaskList and display relevant information with the customized format.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, CommandHistory commandHistory) throws StorageFileException {
        commandHistory.saveState(tasks);
        tasks.addTask(this.task);
        storage.saveTaskList(tasks);
        String message = "Got it. I've added this task:\n" + this.task
                + "\nNow you have " + tasks.getNoOfTasks() + " tasks in the list.";
        ui.appendResponse(message);
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
        if (!(obj instanceof AddTaskCommand)) {
            return false;
        }
        AddTaskCommand ddlObj = (AddTaskCommand) obj;

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
