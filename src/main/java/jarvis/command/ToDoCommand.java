package jarvis.command;

import jarvis.exception.command.MissingParameterException;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.task.ToDoTask;
import jarvis.ui.Ui;


/**
 * Command class for creating a todo task.
 */
public class ToDoCommand extends Command {

    public ToDoCommand(String body) {
        super(Action.CREATE_TODO, body);
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
