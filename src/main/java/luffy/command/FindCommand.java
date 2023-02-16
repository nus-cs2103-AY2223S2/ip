package luffy.command;

import luffy.storage.TaskList;
import luffy.ui.Ui;
import luffy.exception.LuffyException;

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
    public String execute(TaskList taskList, Ui ui) throws LuffyException {
        String response = taskList.printMatchingList(this.keyword);
        return response;
    }
}
