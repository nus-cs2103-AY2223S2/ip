package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UserInterface;

/**
 * Command to unmark a task as completed.
 *
 * @author Samarth Verma
 */
public class UnmarkTask extends Command {

    private int index;

    /**
     * Creates a new UnmarkTask command.
     *
     * @param index The index of the task to be unmarked as completed.
     */
    public UnmarkTask(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, UserInterface ui, Storage storage) throws DukeException {
        try {
            Task task = list.stream().filter(t -> t.id() == index).findFirst().get();
            task.markCompleted();
            ui.showMessage("Nice! I've marked this task as pending: " + task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task with id " + index + " does not exist.");
        }
    }
}
