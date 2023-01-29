package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int lenOfTaskList = tasks.getLength();
        ArrayList<Task> relevantTasks = new ArrayList<>();
        for (int i = 0; i < lenOfTaskList; i++) {
            Task currTask = tasks.getTask(i);
            if (currTask.getDescription().contains(keyword)) {
                relevantTasks.add(currTask);
            }
        }
        ui.showRelevantTasks(relevantTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
