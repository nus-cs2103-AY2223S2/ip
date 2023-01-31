package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class DeleteCommand extends Command {

    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList l, Ui ui, Storage s) {
        Task t = l.remove(index);
        ui.showDelete(t, l);
    }

}
