package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.UserInterface;

public class EventCommand extends Command {
    public static final String COMMAND_FORMAT = "(event /from .+ /to .+)";
    private static final int SUPPOSED_TOKEN_LENGTH = ;
    @Override
    public String execute(TaskList tasks, UserInterface ui, Storage storage) {
        return null;
    }
}
