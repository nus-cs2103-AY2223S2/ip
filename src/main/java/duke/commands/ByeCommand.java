package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates bye command and its arguments.
 */
public class ByeCommand extends Command {
    public static final String COMMAND = "bye";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
        return;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
