package main.java.duke.command;

import main.java.duke.ui.Ui;
import main.java.duke.storage.Storage;

public abstract class Command {
    protected String[] command;
    
    public Command(String[] command) {
        this.command = command;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    public boolean isExit() {
        return false;
    }
}
