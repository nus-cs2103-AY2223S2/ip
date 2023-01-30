package duke.commands;

import java.io.IOException;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(this.index);
        int taskNum = index + 1;
        try {
            storage.writeToFile(tasks);
            ui.showToUser("You have marked task " + taskNum + " as done.");
        } catch (IOException e) {
            ui.showError("Unable to write to file. Please run Duke again.");
        }
    }
}
