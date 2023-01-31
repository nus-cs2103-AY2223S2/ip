package commands;

import exception.TreeBotException;
import tasks.TaskList;
import utils.Storage;
import utils.Ui;

import java.io.IOException;

public class DeleteCommand extends Command{
    private int index;

    /**
     * Returns a Command that when executed deletes a task at a given index of TaskList.
     * @param index
     */
    public DeleteCommand(int index) {
        this.index = index;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TreeBotException {
        taskList.deleteTask(index);
        try {
            storage.saveTasks(taskList.getArrayListCopy());
        } catch (IOException e) {
            throw new TreeBotException(e.getMessage());
        }
    }
}
