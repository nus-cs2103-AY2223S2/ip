package command;

import dukeexception.DukeException;
import store.Storage;
import store.TaskList;
import userinteraction.Ui;

abstract public class Command {
    String[] inputArr;
    public Command(String[] inputArr) {
        this.inputArr = inputArr;
    }
    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
    abstract public boolean isExit();
}
