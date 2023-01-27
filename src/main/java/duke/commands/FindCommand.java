package duke.commands;

import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;

/**
 * Represents the command for finding tasks given a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Tasks tasks, Ui ui, Storage storage) {
        tasks.filterByKeyword(this.keyword);
    }
}
