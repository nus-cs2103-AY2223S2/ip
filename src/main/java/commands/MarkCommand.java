package commands;

import java.io.IOException;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;


/**
 * Represents command for marking a task
 */
public class MarkCommand extends Command {
    public MarkCommand(String string) {
        super(string);
    }

    /**
     * Exucute mark command
     * @param tasks the current list of tasks
     * @param ui the user interface
     * @param storage the storage where the changes done by command action stored
     * @throws IOException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            int idx = Integer.parseInt(super.getCommand()) - 1;
            Task task = tasks.get(idx);
            task.mark();
            storage.mark(idx);
            ui.markMsg();
            ui.printTask(task);
            tasks.set(idx, task);
        } catch (Exception e) {
            ui.idxErrorMsg();
        }
    }
}
