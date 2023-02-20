package Duke.command;

import Duke.Exceptions.DukeMainExceptions;
import Duke.Exceptions.InvalidIndexException;
import Duke.Storage.Storage;
import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Ui;

public class DeleteCommand extends Command {
    private final int selectedIndex;

    public DeleteCommand(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws
            DukeMainExceptions, InvalidIndexException {
        try {
//            Task task = tasks.remove(this.selectedIndex - 1, storage);
            return tasks.delete(selectedIndex - 1);
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidIndexException(tasks.getSize() + 1);
        }
    }
}
