package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.FileWriter;
import java.io.IOException;


public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;
//        throw new UnsupportedOperationException("Method called from main Duke.command class, should be called by child classes");


    public abstract boolean isExit();
}
