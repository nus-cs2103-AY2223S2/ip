package twofive.command;

import twofive.data.TaskList;
import twofive.exception.InvalidTaskException;
import twofive.exception.TaskUndoneException;
import twofive.storage.Storage;
import twofive.task.Task;
import twofive.ui.Ui;

public class UnmarkCommand extends Command {
    private int taskNum;

    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskUndoneException, InvalidTaskException {
        if (this.taskNum < 0 || this.taskNum >= tasks.getTasksNum()) {
            throw new InvalidTaskException();
        } else {
            Task currentTask = tasks.setTaskAsUndone(taskNum);
            ui.showMesssage("OK, I've marked this task as not done yet:\n " + currentTask);
        }
    }
}
