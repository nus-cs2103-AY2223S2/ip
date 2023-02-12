package duke.command;

import duke.storage.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;

/**
 * The FindCommand class encapsulates the variables and methods related to Find commands.
 */
public class FindCommand extends Command {
    private static final String FIND_COMMAND = "find";
    private final String keyword;

    /**
     * Constructor creates an instance of FindCommand.
     * @param keyword String keyword.
     */
    public FindCommand(String keyword) {
        super(FIND_COMMAND);
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        String response = taskList.printMatchingList(this.keyword);
        response += ui.showLine();
        return response;
    }
}
