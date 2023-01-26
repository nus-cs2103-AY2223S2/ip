package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.UserInterface;

public class DeleteCommand extends Command {
    public static final String COMMAND_FORMAT = "delete \\d+";
    private static final int SUPPOSED_TOKEN_LENGTH = 2;
    @Override
    public String execute(TaskList tasks, UserInterface ui, Storage storage) {
        return null;
    }
}
