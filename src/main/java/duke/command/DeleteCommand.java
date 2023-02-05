package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.storage.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList list, Ui ui) {
        String output = ui.printDeleteMessage(list.get(index), list);
        list.delete(index);
        return output;
    }
}
