package book.command;

import book.Storage;
import book.TaskList;
import book.Ui;

/**
 * Implementation of a {@code Command} for finding {@code Task} objects matching a given
 * {@code String} keyword in {@code TaskList}.
 */
public class FindCommand extends Command {
    /** The {@code String} keyword to match. */
    private String keyword;

    /**
     * Initializes an {@code FindCommand} object.
     *
     * @param keyword {@code String} keyword to match with {@code Task} objects in
     *     {@code TaskList}.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Lists the {@code Task} objects matching a given {@code String} keyword in the associated
     * {@code TaskList}, returns the {@code String} to be displayed on the {@code Gui}.
     *
     * @param storage {@code Storage} associated with the {@code Command}.
     * @param list {@code TaskList} associated with the {@code Command}.
     * @param ui {@code Ui} associated with the {@code Command}.
     * @return {@code String} to be displayed on the {@code Gui}.
     */
    public String execute(Storage storage, TaskList list, Ui ui) {
        return ui.showMatchingTasksList(list, this.keyword);
    }
}
