package dude.command;

import dude.storage.Storage;
import dude.task.Task;
import dude.task.TaskList;
import dude.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> filteredTasks = new ArrayList<>();
        int taskCount = Task.count;
        for (int i = 1; i <= taskCount ; i++) {
            Task currentTask = tasks.getTask(i);
            if (currentTask.contains(keyword)) {
                filteredTasks.add(currentTask);
            }
        }
        ui.showFind(filteredTasks);
    }
}
