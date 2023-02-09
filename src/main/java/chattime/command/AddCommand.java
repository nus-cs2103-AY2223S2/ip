package chattime.command;

import java.io.IOException;

import chattime.TaskList;
import chattime.storage.Storage;
import chattime.task.Task;
import chattime.ui.Ui;

/**
 * Represents AddCommand object that handles main logic of adding task ,including todo, deadline and event tasks.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates AddCommand object to execute the task adding logic.
     *
     * @param addedTask Task to be added.
     */
    public AddCommand(Task addedTask) {
        task = addedTask;
    }

    /**
     * Implements and executes main logic of AddCommand object.
     * Adds task to task list and storage file.
     * Returns UI message to notify successful task added.
     *
     * @param ui UI instance of bot.
     * @param taskList Current task list storing tasks.
     * @param storage Storage file to store current state items of task list.
     * @return Bot's reply to user's add task command.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        if (taskList.isDuplicates(task, task.getClass())) {
            Task.removeDuplicate();
            return ui.alertDuplicate();
        }
        taskList.addTask(task);
        return replyDeleteProgress(ui, storage);
    }

    private String replyDeleteProgress(Ui ui, Storage storage) {
        try {
            storage.saveToFile(task);
        } catch (IOException e) {
            return ui.printError(e.getMessage());
        }
        return ui.printAddTask(task, Task.printTotalTask());
    }
}
