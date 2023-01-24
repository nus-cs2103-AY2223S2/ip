package chattime.command;

import chattime.storage.Storage;
import chattime.task.Task;
import chattime.TaskList;
import chattime.ui.Ui;

/**
 * Represents MarkCommand object that handles main logic of marking a task as done or not done.
 */
public class MarkCommand extends Command {
    private int index;
    private boolean done;

    /**
     * Creates MarkCommand object to execute the task marking logic.
     *
     * @param index Index of the task in task list.
     * @param done true if mark as done, false if mark as undone.
     */
    public MarkCommand(int index, boolean done) {
        this.index = index;
        this.done = done;
    }

    /**
     * Implements and executes main logic of MarkCommand object.
     * Gets task from task list.
     * Executes task status marking and returns UI message respectively to user.
     * Updates data in storage file.
     *
     * @param ui UI instance of bot.
     * @param taskList Current task list storing tasks.
     * @param storage Storage file to store current state items of task list.
     */
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
