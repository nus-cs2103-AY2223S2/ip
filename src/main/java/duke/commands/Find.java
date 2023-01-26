package duke.commands;

import duke.data.MyData;
import duke.ui.Ui;

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

    public void execute(MyData data, Ui ui) {
        ui.find(data, keyword);
    }
}
