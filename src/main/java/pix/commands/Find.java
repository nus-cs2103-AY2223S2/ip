package pix.commands;

import pix.data.MyData;
import pix.ui.Ui;

/**
 * Find class which finds tasks in list of task containing a keyword.
 */
public class Find extends Command {
    /** Keyword to search tasks by. */
    private final String keyword;

    /**
     * Constructs a Find command.
     *
     * @param keyword Keyword to search tasks by.
     */
    public Find(String keyword) {
        this.keyword = keyword;
    }

    public String execute(MyData data, Ui ui) {
        return ui.find(data, keyword);
    }
}
