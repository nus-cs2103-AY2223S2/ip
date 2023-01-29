package command;

import duke.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ListTasksCommand extends Command {
    public ListTasksCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String tasksString = tasks.listTasks();
        ui.formResponse(tasksString);
    }
}
