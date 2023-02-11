package tigerlily.commands;

import tigerlily.Storage;
import tigerlily.Ui;
import tigerlily.exceptions.DukeExceptions;
import tigerlily.tasks.TaskList;

public interface Command {
    String execute(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions;
}