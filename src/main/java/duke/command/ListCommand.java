package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        ui.showToUserList(tl);
    }
}
