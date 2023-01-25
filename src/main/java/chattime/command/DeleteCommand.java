package chattime.command;

import chattime.TaskList;
import chattime.storage.Storage;
import chattime.task.Task;
import chattime.ui.Ui;

public class DeleteCommand extends Command {

    private int taskIndex;

    public DeleteCommand(int index) {
        taskIndex = index;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        Task task = taskList.getTask(taskIndex);

        task.removeTask();
        taskList.removeListMember(taskIndex);
        storage.deleteFromFile(taskIndex);
        ui.replyRemoveTaskMsg(task, Task.printTotalTask());
    }

}
