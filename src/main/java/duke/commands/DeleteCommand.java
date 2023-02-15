package duke.commands;

import duke.Storage;
import duke.TaskDeletionException;
import duke.TaskList;

/**
 * Command that deletes an entry
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskDeletionException {
        try {
            tasks.delete(index - 1);
            storage.store(tasks);
            return "Removal successful. New list:\n" + tasks.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskDeletionException("Item not found");
        }
    }
}
