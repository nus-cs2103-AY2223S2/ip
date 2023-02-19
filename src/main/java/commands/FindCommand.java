package commands;

import static commands.CommandType.FIND;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command that finds tasks using a specified keyword.
 */
public class FindCommand extends Command {
    private static final String RESULT_MESSAGE = "Here are the matching tasks in your list:\n ";
    private String keyword;
    /**
     * Constructs a new FindCommand with the specified keyword to search the tasklist for.
     *
     * @param keyword the keyword of the task description to be searched in the tasklist
     */
    public FindCommand(String keyword) {
        super(FIND);
        this.keyword = keyword;
    }

    /**
     * Executes this FindCommand with a specified TaskList, Ui, and Storage.
     * Uses the search function of the TaskList to search for tasks with descriptions
     * that match the keyword, then displaying it to the user using the Ui
     *
     * @param list the TaskList to search for Tasks with the keyword
     * @param ui the Ui to help inform the user of the results of the search
     * @param storage the Storage to save the updated TaskList to (not used in this command)
     *
     * @return The execution result string.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        TaskList results = list.search(keyword);
        StringBuilder sb = new StringBuilder();
        sb.append(RESULT_MESSAGE);
        sb.append(results.toString());
        return sb.toString();
    }
}
