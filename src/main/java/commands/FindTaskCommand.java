package commands;

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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList filteredTasks = taskList.findByKeyword(this.keyword);
        filteredTasks.listItems();
        return filteredTasks.getSize() == 0
                ? "Empty the Jedi Archives are, the task you seek"
                : String.format("%d tasks in the Jedi Archives, I find\n %s",
                filteredTasks.getSize(), filteredTasks.listItems());
    }
}
