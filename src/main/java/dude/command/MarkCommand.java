package dude.command;

import dude.task.Task;
import dude.task.TaskList;
import dude.storage.Storage;
import dude.ui.Ui;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (Task.count >= taskIndex && Task.count != 0) {
            Task currentTask = tasks.getTask(taskIndex);
            currentTask.mark();
            storage.saveData(tasks);
            ui.showMark(currentTask);
        } else {
            ui.showError("\tUhh... Where got this task for me to mark?");
        }
    }
}
