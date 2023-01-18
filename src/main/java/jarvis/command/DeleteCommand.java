package jarvis.command;

import jarvis.Storage;
import jarvis.TaskList;
import jarvis.Ui;
import jarvis.exception.MissingParameterException;


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
