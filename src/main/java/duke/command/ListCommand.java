package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    public String execute(TaskList l, Ui ui, Storage s) {
        return ui.showList(l);
    }
}
