package duke.Command;

import duke.Exception.InvalidTaskException;

import duke.Storage;
import duke.TaskList;
import duke.UI;

public class UnmarkCommand extends Command {

    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws InvalidTaskException {
        if (index < 1 || index > tasks.getItems()) {
            throw new InvalidTaskException(index);
        }
        ui.showConfirmation(tasks.getTasks().get(index - 1).markAsUndone());
        storage.saveToFile(tasks.getTasks());
    }
}
