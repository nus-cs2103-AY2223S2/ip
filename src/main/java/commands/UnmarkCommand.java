package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String string) {
        super(string);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            int idx = Integer.parseInt(super.getCommand()) - 1;
            Task task = tasks.get(idx);
            task.unmark();
            storage.unmark(idx);
            ui.unmarkMsg();
            ui.printTask(task);
            tasks.set(idx, task);
        } catch (Exception e) {
            ui.idxErrorMsg();
        }
    }
}
