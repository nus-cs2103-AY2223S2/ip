package commands;

import exception.TreeBotException;
import tasks.Task;
import tasks.TaskList;
import utils.Storage;
import utils.Ui;

import java.io.IOException;

public class AddCommand extends Command{

    private Task task;

    /**
     * Returns a Command that when executed adds a task to the list of tasks.
     * @param task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TreeBotException {
        taskList.addTask(task);
        try {
            storage.saveTasks(taskList.getArrayListCopy());
        } catch (IOException e) {
            throw new TreeBotException(e.getMessage());
        }

    }
}
