package command;

import duke.Storage;
import duke.Ui;
import task.Task;
import task.Tasklist;

/**
 * The DeleteCommand class represents a Command that implements the "delete" functionality in the task list application.
 * It deletes a task from the task list based on its index.
 */
public class DeleteCommand implements Command {
    private int index;

    /**
     Constructs a new DeleteCommand with the specified task index.
     @param index the index of the task to be deleted.
     */

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     Executes the delete command by calling the {@link Tasklist#deleteTask(int)} method
     with the specified task index.
     @param ui the User Interface component
     @param list the task list
     @param storage the backend storage component
     */
    @Override
    public String execute(Ui ui, Tasklist list, Storage storage) {
        int oneIndex = index + 1;
        Task deletedTask = list.getTask(oneIndex);
        list.deleteTask(this.index);
        return ui.getDeleteReply(deletedTask, list.size());
    }

    /**
     Returns false, as this command does not result in an application exit.
     @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
