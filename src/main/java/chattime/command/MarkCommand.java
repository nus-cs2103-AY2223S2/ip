package chattime.command;

import chattime.storage.Storage;
import chattime.task.Task;
import chattime.task.TaskList;
import chattime.ui.Ui;

public class MarkCommand extends Command {
    private int index;
    private boolean done;

    public MarkCommand(int index, boolean done) {
        this.index = index;
        this.done = done;
    }

    public void execute(Ui ui, TaskList taskList, Storage storage) {
        Task target = taskList.getTask(index);
        if (done) {
            target.markAsDone();
            ui.doneMessage(target);
        } else {
            target.unmarkDone();
            ui.notDoneMessage(target);
        }
        storage.updateFile(index, taskList.getTask(index));
    }

}
