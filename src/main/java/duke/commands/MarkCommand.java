package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.UserInterface;

public class MarkCommand extends Command {
    public static final String COMMAND_FORMAT = "mark \\d+";

    @Override
    public String execute(TaskList tasks, UserInterface ui, Storage storage) {
        return null;
    }

}
