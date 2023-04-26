package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * A class that handles finding task by keyword.
 */
public class FindKeywordCommand extends Command {
    private final String keyword;

    public FindKeywordCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int idx = 0;
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.printDescription(i).contains(keyword)) {
                idx++;
                if (idx == 1) {
                    ui.showToUser("Here are the matching tasks in your list:");
                }
                ui.showToUser(idx + "." + tasks.printTask(i));
            }
        }
        if (idx == 0) {
            ui.showToUser("There's no matching task in your list.");
        }
    }
}
