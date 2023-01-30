package duke.commands;

import java.io.IOException;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;


public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.delete(this.index);
        ui.showToUser("You have deleted: " + deletedTask.toString(),
                "You have " + tasks.getSize() + " tasks in the list.");
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            ui.showError("Unable to write to file. Please run Duke again.");
        }
    }
}
