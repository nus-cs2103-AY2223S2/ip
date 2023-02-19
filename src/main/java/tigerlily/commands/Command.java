package tigerlily.commands;

import tigerlily.util.Storage;
import tigerlily.util.Ui;
import tigerlily.exceptions.DukeExceptions;
import tigerlily.tasks.TaskList;

public interface Command {
    String execute(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions;
}