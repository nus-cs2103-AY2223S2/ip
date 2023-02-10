package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UserInterface;

/**
 * Represents a command that marks a task as completed.
 * 
 * @author Samarth Verma
 */
public class MarkTask extends Command {
    private int id;

    /**
     * Creates a new MarkTask command.
     * @param id The index of the task to be marked as completed.
     */
    public MarkTask(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList list, UserInterface ui, Storage storage) throws DukeException {
        try {
            Task task = list.stream().filter(t -> t.id() == id).findFirst().get();
            task.markCompleted();
            ui.showMessage("Nice! I've marked this task as done: " + task);

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task with id " + id + " does not exist.");
        }
    }
}
