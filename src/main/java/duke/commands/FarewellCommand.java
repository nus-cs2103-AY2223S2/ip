package duke.commands;

import duke.DukeException;
import duke.TaskList;

public class FarewellCommand extends Command {
    public static final String COMMAND_FORMAT = "bye";
    private static final int SUPPOSED_TOKEN_LENGTH = 1;
    public FarewellCommand(String[] tokens) {
        super(tokens);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        return null;
    }

    @Override
    protected void validateCommand() throws DukeException {

    }
}
