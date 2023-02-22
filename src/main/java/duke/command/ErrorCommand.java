package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Represents a command which alerts the user about incorrect input
 */
public class ErrorCommand extends Command {
    public ErrorCommand(String msg) {
        super(msg);
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return this.commandName;

    }
}
