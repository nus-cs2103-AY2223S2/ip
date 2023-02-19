package Duke.command;

import Duke.Exceptions.DukeMainExceptions;
import Duke.Exceptions.InvalidIndexException;
import Duke.Storage.Storage;
import Duke.Tasks.Task;
import Duke.TaskList;
import Duke.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int selectedIndex) {
        this.index = selectedIndex;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws
            DukeMainExceptions, InvalidIndexException {
        try {
            Task task = tasks.mark(this.index);
            return ui.printMarked(task);
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidIndexException(1 + tasks.getSize());
        }
    }

}
