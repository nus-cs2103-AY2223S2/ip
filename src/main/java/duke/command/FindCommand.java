package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command to find a list of tasks whose name contains a search phrase
 */
public class FindCommand extends Command {
    private String searchLine;

    public FindCommand(String searchLine) {
        this.searchLine = searchLine;
    }

    /**
     * Finds all tasks containing the search phrase
     * @param tasks TaskList from which we find the tasks of interest
     * @param ui Ui for displaying messages in a unique way
     * @param storage Storage for updating local tasks
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String result = ui.changeToFormat(tasks.find(searchLine));
        assert result.equals("-->-->-->-->-->-->-->-->-->-->-->\n    " + "The following task is removed:\n    "
            + tasks.find(searchLine) + "\n<--<--<--<--<--<--<--<--<--<--\n\n") : "wrong find message";
        return result;

    }
}
