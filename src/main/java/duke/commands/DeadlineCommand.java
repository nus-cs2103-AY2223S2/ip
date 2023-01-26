package duke.commands;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;
import duke.tasks.DeadlineTask;

public class DeadlineCommand extends Command {
    public static final String COMMAND_FORMAT = "deadline";
    private static final int SUPPOSED_TOKEN_LENGTH = 3;
    private static final String DEADLINE_PREFIX = "/by";
    public DeadlineCommand(String[] tokens) {
        super(tokens);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        validateCommand();

        String taskName = tokens[0];
        String deadline = tokens[2];
        String result = tasks.addTask(DeadlineTask.createTask(taskName, deadline));
        return result;
    }

    @Override
    protected void validateCommand() throws DukeException {
        if (!(tokens.length == SUPPOSED_TOKEN_LENGTH)
                || !tokens[1].equals(DEADLINE_PREFIX)) {
            throw new DukeException(Parser.INVALID_COMMAND_EXCEPTION_MESSAGE);
        }
    }
}
