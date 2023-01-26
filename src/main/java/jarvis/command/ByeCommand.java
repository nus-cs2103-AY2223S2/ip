package jarvis.command;

import jarvis.exception.TaskIoException;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;


/**
 * Command class to exit the bot.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        super(Action.BYE, null);
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            storage.saveTasks(taskList.getTasks());
        } catch (TaskIoException e) {
            ui.printError(e.getFriendlyMessage());
        }

        ui.printStandard(Ui.Response.GOODBYE);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
