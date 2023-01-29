package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;


public class UnmarkCommand extends Command {
    private int taskNum;

    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToBeUnmarked = tasks.getTask(taskNum - 1);
        taskToBeUnmarked.markAsIncomplete();
        ui.showUnmarkedMsg(taskToBeUnmarked);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
