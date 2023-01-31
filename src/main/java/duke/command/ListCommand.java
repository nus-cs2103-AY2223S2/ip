package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    public void execute(TaskList l, Ui ui, Storage s) {
        ui.showList(l);
    }
}
