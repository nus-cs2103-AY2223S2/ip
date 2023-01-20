package jarvis.command;

import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;
import jarvis.exception.MissingParameterException;


/**
 * Command class to delete tasks.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(Action action, String body) {
        super(action, body, null);

        int index = -1;
        try {
            index = Integer.parseInt(body);
        } catch (NumberFormatException ignored) {}
        this.index = index;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            ui.print(taskList.deleteTask(this.index));
        } catch (MissingParameterException e) {
            ui.printError(e.getFriendlyMessage());
        }
    }
}
