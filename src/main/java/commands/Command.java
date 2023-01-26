package commands;

import ui.Ui;
import commands.*;
import storage.*;
import parser.*;
import tasks.*;

import exceptions.DukeException;
public class Command {

    public boolean isExit() {
        return false;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    };
}
