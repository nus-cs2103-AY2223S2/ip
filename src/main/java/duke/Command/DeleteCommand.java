package duke.Command;

import duke.Exception.InvalidTaskException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * The Delete command which is executed by Duke.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * The constructor for an (executable) Delete Command.
     * @param index Index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * If the index of the task input is valid (exists in task list), delete the corresponding task from the
     * task list.
     * The confirmation is then displayed on the UI, and the changes to task list are saved to file.
     * If not, throw an exception.
     * @param tasks The task list to add to/edit/delete from.
     * @param ui The UI to display the confirmation messages/error messages to users.
     * @param storage The storage which to store to when a task is added/deleted or its status is changed.
     * @throws InvalidTaskException Exception thrown when the index is invalid (does not exist in the task list
     * or cannot exist in the task list (negative numbers)).
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws InvalidTaskException {
        if (index < 1 || index > tasks.getItems()) {
            throw new InvalidTaskException(index);
        }
        ui.showConfirmation(tasks.deleteTask(index -1));
        storage.saveToFile(tasks.getTasks());
    }
}
