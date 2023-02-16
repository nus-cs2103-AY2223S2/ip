package genie.command;

import genie.main.Storage;
import genie.main.TaskList;
import genie.main.Ui;
import genie.task.Task;

import java.util.ArrayList;

/**
 * Deals with execution of 'find'
 */
public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the required actions for 'find' and generates its corresponding response.
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = taskList.searchMatchingTasks(keyword);
        ui.printMatchingTaskList(matchingTasks);
    }

    /**
     * Checks if command is 'bye'
     * @return true if is 'bye', false otherwise
     */
    @Override
    public boolean isExitCommand() {
        return false;
    }
}
