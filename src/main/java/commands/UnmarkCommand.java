package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command{

    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showToUser("OK, I've marked this task as not done yet:");
        tasks.unmarkTask(idx - 1);
        try {
            storage.writeMarkingToFile(storage.getFilePath(), "0", idx - 1);
        } catch(IOException e) {
            throw new DukeException("Error overwriting");
        }
        ui.showToUser("    " + idx + "." + tasks.printTask(idx - 1));
    }
}
