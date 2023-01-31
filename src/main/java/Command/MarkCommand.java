package Command;

import Main.TaskList;
import Main.Storage;
import Main.DukeException;
import Main.Ui;
import Task.Task;

public class MarkCommand extends Command {
    int index;

    public MarkCommand(int index) {
        this.index = index - 1;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index > taskList.getTotalNumberOfTask()) {
            throw new DukeException("Index too large");
        } else if (index <= 0) {
            throw new DukeException("Index too small");
        }
        Task t = taskList.taskDone(index);
        storage.writeFile(taskList);
        ui.outputMark(t);
    }
}
