package main.java.duke.command;

import main.java.duke.ui.Ui;
import main.java.duke.storage.Storage;

public class ListCommand extends Command {
    
    public ListCommand(String[] command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }
}