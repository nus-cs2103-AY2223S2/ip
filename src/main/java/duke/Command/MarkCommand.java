package duke.Command;

import duke.Exception.InvalidTaskException;

import duke.Storage;
import duke.TaskList;
import duke.UI;

public class MarkCommand extends Command {

    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws InvalidTaskException {
        if (index < 1 || index > tasks.getItems()) {
            throw new InvalidTaskException(index);
        }
        ui.showConfirmation(tasks.getTasks().get(index - 1).markAsDone());
        storage.saveToFile(tasks.getTasks());
    }
}
