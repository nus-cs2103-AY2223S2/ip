package jarvis.command;

import jarvis.Storage;
import jarvis.task.TaskList;
import jarvis.Ui;
import jarvis.exception.TaskIOException;


/**
 * Command class to exit the bot.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        super(null, null, null);
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            storage.saveTasks(taskList.getTasks());
        } catch (TaskIOException e) {
            ui.printError(e.getFriendlyMessage());
        }

        ui.printStandard(Ui.Response.GOODBYE);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
