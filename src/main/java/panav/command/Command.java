package panav.command;

import panav.exception.DukeException;

import panav.storage.Storage;

import panav.task.TaskList;

import panav.ui.Ui;

public abstract class Command {

    private String commandName;
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
