package main.java.duke.command;

import main.java.duke.ui.Ui;
import main.java.duke.storage.Storage;
import main.java.duke.task.TaskList;

public class ByeCommand extends Command {
    
    public ByeCommand(String[] command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodBye();
    }

    public boolean isExit() {
        return true;
    }
}