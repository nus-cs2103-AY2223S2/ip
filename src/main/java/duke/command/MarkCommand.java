package duke.command;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList list, Ui ui) {
        try {
            list.get(index);
        }
        catch (IndexOutOfBoundsException e) {
            return ui.printOutOfBoundsMessage(list);
        }
        list.mark(index);
        return ui.printMarkMessage(list.get(index));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
