package duke.command;

import duke.Tasklist;

/**
 * Class representing the find command.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for the FindCommand class.
     *
     * @param keyword Keyword of the search.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(Tasklist tasklist, int saveNo) throws Exception {
        String returnedString = tasklist.findTasks(this.keyword);
        return returnedString;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
