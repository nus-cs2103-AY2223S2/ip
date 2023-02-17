package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Represents command for deleting a task
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String string) {
        super(string);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            int idx = Integer.parseInt(super.getCommand()) - 1;
            Task task = tasks.get(idx);
            tasks.delete(idx);
            storage.delete(idx);
            ui.removeMsg();
            ui.printTask(task);
            ui.printListSize(tasks);
        } catch (Exception e) {
            ui.idxErrorMsg();
        }
    }
}
