package jarvis.command;

import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;
import jarvis.exception.MissingParameterException;
import jarvis.task.ToDoTask;


/**
 * Command class for creating a todo task.
 */
public class ToDoCommand extends Command {

    public ToDoCommand(Action action, String body) {
        super(action, body, null);
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            ui.print(taskList.addTask(new ToDoTask(this.getBody())));
        } catch (MissingParameterException e) {
            ui.printError(e.getFriendlyMessage());
        }
    }
}
