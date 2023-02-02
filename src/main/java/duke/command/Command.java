package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public interface Command {
    String getCommandName();

    String getCommandRegexPattern();

    String getCommandPattern();

    void run(Ui ui, TaskList taskList, Storage storage, String... args) throws DukeException;
}
