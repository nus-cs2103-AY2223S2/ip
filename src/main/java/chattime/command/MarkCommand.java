package chattime.command;

import chattime.TaskList;
import chattime.storage.Storage;
import chattime.task.Task;
import chattime.ui.Ui;

public class MarkCommand extends Command {
    private int taskIndex;
    private boolean isDone;

    public MarkCommand(int index, boolean taskIsDone) {
        taskIndex = taskIndex;
        isDone = taskIsDone;
    }

    public void execute(Ui ui, TaskList taskList, Storage storage) {
        Task target = taskList.getTask(taskIndex);

        if (isDone) {
            target.markAsDone();
            ui.replyDoneMessage(target);
        } else {
            target.unmarkDone();
            ui.replyNotDoneMessage(target);
        }

        storage.updateFile(taskIndex, taskList.getTask(taskIndex));
    }

}
