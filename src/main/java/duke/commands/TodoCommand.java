package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.UserInterface;

public class TodoCommand extends Command {
    public static final String COMMAND_FORMAT = "(todo .+)";


    @Override
    public String execute(TaskList tasks, UserInterface ui, Storage storage) {
        return null;
    }
}
