package tigerlily.commands;

import tigerlily.Storage;
import tigerlily.Ui;
import tigerlily.exceptions.DukeExceptions;
import tigerlily.tasks.TaskList;

public interface Command {
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions;
}