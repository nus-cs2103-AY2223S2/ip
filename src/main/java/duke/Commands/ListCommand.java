package duke.Commands;

import duke.Exceptions.DukeException;
import duke.Storage.Storage;
import duke.TaskList.TaskList;
import duke.Ui.Ui;

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
