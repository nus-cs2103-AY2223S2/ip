package command;

import dukeexception.CommandException;
import dukeexception.MarkIndexDoesNotExistException;
import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

public class MarkCommand extends Command {

    public MarkCommand(String input) {
        super(input);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int id = Integer.parseInt(this.unwrap()[0]);
            int index = id - 1;
            if (index >= tasks.size()) {
                throw new MarkIndexDoesNotExistException("☹ OOPS!!! mark index does not exist");
            }

            Task task = tasks.get(index);
            task.markAsDone();
            ui.showMessage(String.format("Nice! I've marked this task as done:\n%s", task));

            storage.save(tasks);
        } catch (CommandException | MarkIndexDoesNotExistException error) {
            ui.showError(error);
        }
    }
}
