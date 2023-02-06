package duke.commands;

import duke.Storage;
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
    public String execute(TaskList tasks, Storage storage) {
        try {
            tasks.delete(index - 1);
            storage.store(tasks);
            return "Removal successful. New list:\n" + listAll(tasks);
        } catch (IndexOutOfBoundsException e) {
            return "Item does not exist";
        }
    }
}
