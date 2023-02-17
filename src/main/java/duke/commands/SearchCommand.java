package duke.commands;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;

/**
 * Command that allows user to search the list of tasks with keywords
 */
public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";
    private static final String SEARCH_RESPONSE = "Here are the task search results:";

    /**
     * Constructor for command that searches through list of tasks
     *
     * @param input User's input
     */
    public SearchCommand(String input) {
        super(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        String input = getInput();
        try {
            int firstWhitespaceIndex = input.indexOf(" ");
            if (firstWhitespaceIndex == -1) {
                throw new IndexOutOfBoundsException();
            }
            String keyword = input.substring(firstWhitespaceIndex + 1);

            String result = tasks.search(keyword);
            return SEARCH_RESPONSE + result;
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException(Parser.INVALID_COMMAND_EXCEPTION_MESSAGE);
        }
    }
}
