package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * The ByeCommand class encapsulates the variables and methods related to bye commands.
 */
public class ByeCommand extends Command {
    public static final String BYE_COMMAND = "bye";

    public ByeCommand() {
        super(BYE_COMMAND);
    }

    @Override
    public String execute(TaskList lst, Ui ui) {
        return ui.showExit();
    }
}
