package commands;
import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

public class ListTasks implements Command{
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
