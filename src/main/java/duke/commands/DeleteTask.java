package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UserInterface;

/**
 * Command to delete a task.
 *
 * @author Samarth Verma
 */
public class DeleteTask extends Command {

    private int index;

    /**
     * Creates a new DeleteTask command.
     *
     * @param id The id of the task to be deleted.
     */
    public DeleteTask(int id) {
        this.index = id;
    }

    @Override
    public void execute(TaskList list, UserInterface ui, Storage storage) throws DukeException {
        try {
            Task task = list.stream().filter(t -> t.id() == index).findFirst().get();
            list.remove(task);
            ui.showMessage("Nice! I've deleted the task: " + list.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task with id " + index + " does not exist.");
        }
    }
}
