package smartduke.command;

import smartduke.DukeException;
import smartduke.TaskList;
import smartduke.Ui;
import smartduke.task.Task;

import java.util.Iterator;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        Iterator<Task> tasksIterator = tasks.list().iterator();
        if (!tasksIterator.hasNext()) {
            ui.showSuccess("There are currently no tasks in your list.");
            return;
        }
        ui.showSuccess("Here are the tasks in your list:");
        int taskNo = 1;
        while (tasksIterator.hasNext()) {
            Task task = tasksIterator.next();
            ui.showSuccess(taskNo + ". " + task.toString());
            taskNo += 1;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
