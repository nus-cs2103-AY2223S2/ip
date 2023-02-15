package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * Represents a default not found command
 */
public class NotFoundCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Command not found";
    }
}
