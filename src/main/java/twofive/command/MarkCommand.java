package twofive.command;

import twofive.data.TaskList;
import twofive.exception.InvalidTaskException;
import twofive.exception.TaskDoneException;
import twofive.storage.Storage;
import twofive.task.Task;
import twofive.ui.Ui;

public class MarkCommand extends Command {
    private int taskNum;

    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskDoneException, InvalidTaskException {
        if (this.taskNum < 0 || this.taskNum >= tasks.getTasksNum()) {
            throw new InvalidTaskException();
        } else {
            Task currentTask = tasks.setTaskAsDone(taskNum);
            ui.showMesssage("Nice! Congrats for completing this task:\n " + currentTask);
        }
    }
}
