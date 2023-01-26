package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class MarkCommand extends Command{

    private int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showToUser("Nice! I've marked this task as done:");
        tasks.markTask(idx - 1);
        try {
            storage.writeMarkingToFile(storage.getFilePath(), "1", idx - 1);
        } catch(IOException e) {
            throw new DukeException("Error overwriting");
        }
        ui.showToUser("    " + idx + "." + tasks.printTask(idx - 1));
    }
}
