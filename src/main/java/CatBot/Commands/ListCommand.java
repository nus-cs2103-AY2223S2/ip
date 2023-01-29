package CatBot.Commands;

import CatBot.Storage.Storage;
import CatBot.TaskList.Task;
import CatBot.TaskList.TaskList;
import CatBot.Ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder taskList = new StringBuilder("List of tasks: \n");
        int index = 1;
        for (Task task: tasks) {
            taskList.append("  ").append(index++).append(".").append(task).append("\n");
        }

        ui.setNextOutput(taskList.toString());
    }
}
