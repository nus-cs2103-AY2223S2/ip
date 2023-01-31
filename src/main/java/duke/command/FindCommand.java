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
        return ui.changeToFormat(tasks.find(searchLine));
    }
}
