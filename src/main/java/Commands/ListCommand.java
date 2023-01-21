package Commands;

import Exceptions.DukeException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class ListCommand implements Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() == 0)
            ui.printNoTasks();
        else
            ui.printTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
