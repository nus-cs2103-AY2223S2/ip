package command;

import smartduke.DukeException;
import smartduke.TaskList;
import smartduke.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui) throws DukeException;
    public abstract boolean isExit();
}
