package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.UserInterface;

public class UnmarkCommand extends Command {
    public static final String COMMAND_FORMAT = "unmark \\d+";
    @Override
    public String execute(TaskList tasks, UserInterface ui, Storage storage) {
        return null;
    }
}
