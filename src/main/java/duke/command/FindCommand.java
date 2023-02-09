package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to Look for tasks with a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.find(keyword);
    }
}
