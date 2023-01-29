package duke.command;

import duke.TaskList;
import duke.command.Command;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    protected int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList list, Ui ui, Storage storage) {
        Task task = list.delete(index);
        ui.delete(task, list.getLength());
        storage.write(list);
    }

    public boolean isExit() {
        return false;
    }
}
