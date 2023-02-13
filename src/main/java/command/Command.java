package command;

import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import userinteraction.Ui;

abstract public class Command {
    private final String input;
    public Command(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }
    public abstract void process(TaskList taskList, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}