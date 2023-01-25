package Duke.Tasks;

import Duke.Command.Command;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

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
