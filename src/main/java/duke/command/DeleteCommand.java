package command;

import dukeexception.CommandException;
import dukeexception.StorageException;
import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String input) {
        super(input);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] values = this.unwrap();
            int index = Integer.parseInt(values[0]) - 1;

            Task task = tasks.delete(index);
            ui.showTaskRemoved(task, tasks.size());

            storage.save(tasks);
        } catch (CommandException | StorageException error) {
            ui.showError(error);
        }
    }
}
