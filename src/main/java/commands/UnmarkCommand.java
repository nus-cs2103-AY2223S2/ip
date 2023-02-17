package commands;

import java.io.IOException;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents command for un-marking a task
 */
public class UnmarkCommand extends Command {
    public UnmarkCommand(String string) {
        super(string);
    }

    /**
     * Execute unmark command
     * @param tasks the current list of tasks
     * @param ui the user interface
     * @param storage the storage where the changes done by command action stored
     * @throws IOException
     */
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

    public String generate(TaskList tasks, Ui ui, Storage storage) {
        int idx = Integer.parseInt(super.getCommand()) - 1;
        Task task = tasks.get(idx);
        task.unmark();
        return ui.printUnmark() + task.toString();
    }
}
