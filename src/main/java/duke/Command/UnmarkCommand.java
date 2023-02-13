package duke.Command;

import duke.Exception.InvalidArgumentsException;
import duke.Exception.InvalidTaskException;

import duke.Utilities.NoteList;
import duke.Utilities.Storage;
import duke.Utilities.TaskList;
import duke.Utilities.UI;

/**
 * The unmark command which is executed by duke.Utilities.Duke.
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * The constructor for a unmark command object.
     * @param index User input after the unmark command. If it is not in proper format, throw exception.
     * @throws InvalidArgumentsException Thrown if the arguments are not in the proper format.
     */
    public UnmarkCommand(String index) throws InvalidArgumentsException {
        try {
            int numToUnmark = Integer.parseInt(index);
            this.index = numToUnmark;
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException();
        }
    }

    /**
     * If the index of the task input is valid (exists in task list), mark the corresponding task from the
     * task list as undone.
     * The confirmation is then displayed on the UI, and the changes to task list are saved to file.
     * If not, throw an exception.
     * @param tasks The task list to add to/edit/delete from.
     * @param ui The UI to display the confirmation messages/error messages to users.
     * @param storage The storage which to store to when a task is added/deleted or its status is changed.
     * @throws InvalidTaskException Exception thrown when the index is invalid (does not exist in the task list
     * or cannot exist in the task list (negative numbers)).
     */
    @Override
    public String execute(TaskList tasks, NoteList notes, UI ui, Storage storage) throws InvalidTaskException {
        if (index < 1 || index > tasks.getItems()) {
            throw new InvalidTaskException(index);
        }
        String confirmationMessage = tasks.getTasks().get(index - 1).markAsUndone();
        storage.saveToFile(tasks.getTasks(), notes.getNotes());
        return confirmationMessage;
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
