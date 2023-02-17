package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.UI.UI;

public class ByeCommand extends Command {
    public static final String COMMAND = "bye";

    @Override
    public void runCommand(TaskList taskList, UI ui, Storage storage) {
        ui.goodbyeResponse();
        return;
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
