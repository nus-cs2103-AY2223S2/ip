package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeExceptions;
import duke.tasks.TaskList;

public interface Command {
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions;
}