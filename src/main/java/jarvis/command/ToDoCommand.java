package jarvis.command;

import jarvis.Storage;
import jarvis.task.TaskList;
import jarvis.Ui;
import jarvis.exception.MissingParameterException;
import jarvis.task.ToDoTask;


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
