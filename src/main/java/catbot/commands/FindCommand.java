package catbot.commands;

import catbot.CatBotException;
import catbot.storage.Storage;
import catbot.tasklist.Task;
import catbot.tasklist.TaskList;
import catbot.ui.Ui;

/**
 * Handles searching for tasks which match a certain keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CatBotException {
        StringBuilder taskList = new StringBuilder("Found these tasks: \n");
        int index = 1;
        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                taskList.append("  ").append(index).append(".").append(task).append("\n");
            }
            index++;
        }

        ui.setNextOutput(taskList.toString());
    }
}
