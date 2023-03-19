package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command when user make a mistake when inputting the fields
 */
public class InvalidCommand extends Command {
    public InvalidCommand(String command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.invalidCmdMsg();
    }

    public String generate(TaskList tasks, Ui ui, Storage storage) {
        return ui.printInvalid();
    }
}
