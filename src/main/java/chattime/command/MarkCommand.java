package chattime.command;

import java.io.IOException;

import chattime.TaskList;
import chattime.storage.Storage;
import chattime.task.Task;
import chattime.ui.Ui;

/**
 * Represents MarkCommand object that handles main logic of marking a task as done or not done.
 */
public class MarkCommand extends Command {
    private int taskIndex;
    private boolean isDone;

    /**
     * Creates MarkCommand object to execute the task marking logic.
     *
     * @param index The index of the task in task list.
     * @param taskIsDone The parameter is true if mark as done, false if mark as undone.
     */
    public MarkCommand(int index, boolean taskIsDone) {
        taskIndex = index;
        isDone = taskIsDone;
    }

    /**
     * Implements and executes main logic of MarkCommand object.
     * Gets task from task list.
     * Executes task status marking and returns UI message respectively to user.
     * Updates data in storage file.
     *
     * @param ui The UI instance of bot.
     * @param taskList The current task list storing tasks.
     * @param storage The storage file to store current state items of task list.
     * @return The bot's reply to user's task mark or unmark done command.
     */
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        Task target = taskList.getTask(taskIndex);

        if (isDone) {
            return markDone(target, ui, taskList, storage);
        } else {
            return unmarkDone(target, ui, taskList, storage);
        }
    }

    private String markDone(Task target, Ui ui, TaskList taskList, Storage storage) {
        target.markAsDone();
        try {
            storage.rewriteFile(taskIndex, taskList.getTask(taskIndex));
        } catch (IOException ex) {
            return ui.printError(ex.getMessage());
        }
        return ui.replyDoneMessage(target);
    }

    private String unmarkDone(Task target, Ui ui, TaskList taskList, Storage storage) {
        target.unmarkDone();
        try {
            storage.rewriteFile(taskIndex, taskList.getTask(taskIndex));
        } catch (IOException ex) {
            return ui.printError(ex.getMessage());
        }
        return ui.replyNotDoneMessage(target);
    }

}
