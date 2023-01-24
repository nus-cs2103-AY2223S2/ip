package commands;

import exceptions.DukeException;
import files.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Command for Duke to find tasks pertaining to a keyword.
 */
public class FindTaskCommand extends Command {

    private String keyword;
    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList filteredTasks = taskList.findByKeyword(this.keyword);
        filteredTasks.listItems();
    }
}
