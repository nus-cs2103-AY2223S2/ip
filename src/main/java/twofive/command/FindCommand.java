package twofive.command;

import twofive.data.TaskList;
import twofive.storage.Storage;
import twofive.ui.Ui;

/**
 * Filters task with description containing with keyword when the command is executed.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(tasks.getKeywordString(this.keyword));
    }
}
