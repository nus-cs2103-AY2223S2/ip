package pix.commands;

import pix.data.MyData;
import pix.ui.Ui;

/**
 * FindCommand class which finds tasks in list of task containing a keyword.
 */
public class FindCommand extends Command {
    /** Keyword to search tasks by. */
    private final String keyword;

    /**
     * Constructs a FindCommand.
     *
     * @param keyword Keyword to search tasks by.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public String execute(MyData data, Ui ui) {
        return ui.find(data, keyword);
    }
}
