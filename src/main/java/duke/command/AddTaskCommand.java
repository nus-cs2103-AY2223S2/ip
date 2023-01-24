package duke.command;

import duke.exception.StorageFileException;
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StorageFileException {
        tasks.addTask(task);
        storage.saveTaskList(tasks);
        String message = "Got it. I've added this task:\n " + task
                + "\nNow you have " + tasks.getNoOfTasks() + " tasks in the list.";
        ui.appendResponse(message);
    }

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

    @Override
    public String toString() {
        return "Add Task: " + this.task;
    }
}
