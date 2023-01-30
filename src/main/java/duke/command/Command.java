package duke.command;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.TaskList;
import duke.exceptions.DirectoryNotFoundException;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;



public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws FileNotFoundException, IllegalArgumentException, DukeException, DirectoryNotFoundException, IOException;

    public abstract boolean isExit();
}
