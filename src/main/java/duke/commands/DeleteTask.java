package duke.commands;

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
    public void execute(TaskList list, UserInterface ui, Storage storage) throws Exception {
        try {
            Task task = list.get(index - 1);
            list.remove(task);
            ui.showMessage("Noted. I've removed this task: " + task);
            storage.save(list);
        } catch (IndexOutOfBoundsException e) {
            throw new Exception("The task with id " + index + " does not exist.");
        }
    }
}
