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

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String result = ui.changeToFormat(tasks.find(searchLine));
        assert result.equals("-->-->-->-->-->-->-->-->-->-->-->\n    " + "The following task is removed:\n    "
            + tasks.find(searchLine) + "\n<--<--<--<--<--<--<--<--<--<--\n\n") : "wrong find message";
        return result;

    }
}
