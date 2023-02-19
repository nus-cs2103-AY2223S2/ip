package Duke.command;

import Duke.Exceptions.DukeMainExceptions;
import Duke.Exceptions.InvalidIndexException;
import Duke.Storage.Storage;
import Duke.Tasks.Task;
import Duke.TaskList;
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
            Task task = tasks.remove(this.selectedIndex, storage);
            return ui.printDeletedTask(task);
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidIndexException(1 + tasks.getSize());
        }
    }
}
