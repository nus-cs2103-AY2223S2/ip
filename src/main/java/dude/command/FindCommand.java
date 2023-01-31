package dude.command;

import java.util.ArrayList;
import java.util.List;

import dude.storage.Storage;
import dude.task.Task;
import dude.task.TaskList;
import dude.ui.Ui;

/**
 * Command to find Tasks with same keyword
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> filteredTasks = new ArrayList<>();
        int taskCount = Task.getTaskCount();
        for (int i = 1; i <= taskCount; i++) {
            Task currentTask = tasks.getTask(i);
            if (currentTask.contains(keyword)) {
                filteredTasks.add(currentTask);
            }
        }
        return ui.showFind(filteredTasks);
    }
}
