package luffy.command;

import luffy.storage.TaskList;
import luffy.ui.Ui;

/**
 * The ByeCommand class encapsulates the variables and methods related to bye commands.
 */
public class ByeCommand extends Command {
    private static final String BYE_COMMAND = "bye";

    public ByeCommand() {
        super(BYE_COMMAND);
    }

    @Override
    public String execute(TaskList taskList, Ui ui) {
        return ui.showExit();
    }
}
