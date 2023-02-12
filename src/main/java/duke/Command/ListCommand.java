package duke.Command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showConfirmation(tasks.printTaskList());
    }
}
