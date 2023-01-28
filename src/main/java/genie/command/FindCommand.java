package genie.command;

import genie.Storage;
import genie.TaskList;
import genie.Ui;
import genie.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = taskList.searchMatchingTasks(keyword);
        ui.printMatchingTaskList(matchingTasks);
    }
    @Override
    public boolean isExitCommand() {
        return false;
    }
}
