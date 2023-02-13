package jarvis.command;

import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;


/**
 * Command class for an unrecognised command.
 */
public class UnknownCommand extends Command {

    public UnknownCommand() {
        super(null, null);
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.printStandard(Ui.Response.CONFUSED);
    }
}
