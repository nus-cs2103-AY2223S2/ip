package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;


public class Delete extends Command {
    private Integer index;

    public Delete(Integer i) {
        this.index = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index > tasks.size() || index < 1) {
            throw new DukeException("Please specify a valid task number.");
        }
        Task t = tasks.get(index-1);
        tasks.remove(index-1);
        ui.showDelete(t, tasks.size());
    }
}
