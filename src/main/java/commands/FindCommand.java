package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents command for finding a certain keyword that match the content of Task
 */
public class FindCommand extends Command {
    public FindCommand(String command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.find(super.getCommand());
    }
}
