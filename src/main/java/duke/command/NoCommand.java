package main.java.duke.command;

import main.java.duke.ui.Ui;
import main.java.duke.storage.Storage;

public class NoCommand extends Command {
    
    public NoCommand(String[] command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.unknownMsg();
    }
}