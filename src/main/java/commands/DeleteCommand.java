package commands;

import java.io.IOException;

import storage.Storage;
import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents command for deleting a task
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String string) {
        super(string);
    }

    /**
     * Execute Delete command
     * @param tasks the current list of tasks
     * @param ui the user interface
     * @param storage the storage where the changes done by command action stored
     * @throws IOException
     */
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

    public String generate(TaskList tasks, Ui ui, Storage storage) {
        int idx = Integer.parseInt(super.getCommand()) - 1;
        Task task = tasks.get(idx);
        return ui.printRemove() + task.toString();
    }
}
