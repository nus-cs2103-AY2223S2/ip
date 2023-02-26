package chattime.command;

import java.io.IOException;

import chattime.TaskList;
import chattime.storage.Storage;
import chattime.task.Task;
import chattime.ui.Ui;

/**
 * Represents DeleteCommand object that handles main logic of deleting task from task list.
 */
public class DeleteCommand extends Command {

    private int taskIndex;

    /**
     * Creates DeleteCommand object to execute the task deleting logic.
     *
     * @param index The index of task to be deleted.
     */
    public DeleteCommand(int index) {
        taskIndex = index;
    }

    /**
     * Implements and executes main logic of DeleteCommand object.
     * Gets specified task object from task list, reduces total object count and removes it from task list.
     * Deletes the task from storage file record.
     * Returns successful delete message to user.
     *
     * @param ui The UI instance of bot.
     * @param taskList The current task list storing tasks.
     * @param storage The storage file to store current state items of task list.
     * @return The bot's reply to user's delete task command.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        Task task = taskList.getTask(taskIndex);

        task.removeTask();
        taskList.removeTask(taskIndex);
        return replyDeleteProgress(task, ui, storage);
    }

    private String replyDeleteProgress(Task task, Ui ui, Storage storage) {
        try {
            storage.rewriteFile(taskIndex);
        } catch (IOException e) {
            return ui.printError(e.getMessage());
        }
        return ui.replyRemoveTaskMsg(task, Task.printTotalTask());
    }

}
