package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;
import duke.task.TaskList;

public abstract class AddTaskCommand implements Command {
    protected void addTask(Task task, Ui ui, TaskList taskList, Storage storage) throws DukeException {
        ui.showLine();

        taskList.addTask(task);
        ui.showText("Got it. I've added this task:");
        ui.showText(String.format("  %s", task.toString()));
        ui.showText(String.format("Now you have %d tasks in the list.", taskList.getTotalTasks()));

        ui.showLine();

        storage.save(taskList.getAllTasks());
    }
}
