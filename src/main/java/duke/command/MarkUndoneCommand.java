package duke.command;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.TaskList;
import duke.exceptions.DirectoryNotFoundException;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;


public class MarkUndoneCommand extends Command {

    protected int index;

    public MarkUndoneCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws FileNotFoundException,
            IllegalArgumentException, DukeException, DirectoryNotFoundException, IOException {
        Task marked = tasks.unmark(index);
        ui.unmark(marked);
        storage.write(tasks);
    }

    public boolean isExit() {
        return false;
    }

}
