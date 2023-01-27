package duke.commands;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;
import duke.tasks.DeadlineTask;

/**
 * Command that allows user to search the list of tasks with keywords
 */
public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";

    public SearchCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        String input = super.input;
        try {
            String[] tokens = input.split(" ");
            String keyword = tokens[1];

            String result = tasks.search(keyword);
            return result;
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new DukeException(Parser.INVALID_COMMAND_EXCEPTION_MESSAGE);
        }
    }
}
