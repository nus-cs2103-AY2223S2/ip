package command;

import main.TaskList;
import main.Storage;
import main.DukeException;
import main.Ui;
import task.Task;

public class UnmarkCommand extends Command{

    int index;

    public UnmarkCommand(int index) {
            this.index = index - 1;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index > taskList.getTotalNumberOfTask()) {
            throw new DukeException("Index too large");
        } else if (index <= 0) {
            throw new DukeException("Index too small");
        }
        Task t = taskList.taskNotDone(index);
        storage.writeFile(taskList);
        ui.outputUnmark(t);
    }
}

