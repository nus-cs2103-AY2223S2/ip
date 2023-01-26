package duke.commands;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public ListCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        String input = getInput();
        if (!input.equals(COMMAND_WORD)) {
            throw new DukeException(Parser.INVALID_COMMAND_EXCEPTION_MESSAGE);
        }
        return tasks.toString();
    }
}
