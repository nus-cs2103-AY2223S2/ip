package duke.command;

import duke.main.Storage;
import duke.main.Tasklist;
import duke.main.Ui;

public class ListCommand extends Command {

    public String execute(Tasklist tasklist, Ui ui, Storage storage) {
        return ui.printTasks(tasklist);
    }
}