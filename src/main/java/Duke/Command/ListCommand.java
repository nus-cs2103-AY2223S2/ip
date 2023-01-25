package Duke.Command;

import Duke.Command.Command;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class ListCommand extends Command {

    public void execute(TaskList l, Ui ui, Storage s) {
        ui.showList(l);
    }
}
