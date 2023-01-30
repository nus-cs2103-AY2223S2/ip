package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.FileNotFoundException;

public abstract class Command {
    public abstract boolean isExit();
    public abstract void executeCommand(TaskList taskList, Storage storage, Ui ui) throws FileNotFoundException;

}
