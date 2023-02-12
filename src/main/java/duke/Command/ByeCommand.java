package duke.Command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.getFarewellMessage();
        System.exit(0);
    }
}
