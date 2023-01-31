package commands;

import exception.TreeBotException;
import tasks.TaskList;
import utils.Storage;
import utils.Ui;

import java.io.IOException;

public class ToggleMarkTaskCommand extends Command {
    private int index;
    private boolean isMarkAsDone;

    /**
     * Returns a Command that when executed marks or unmarks the task in the TaskList.
     * If isMarkAsDone is true, the task is marked as done.
     * If isMarkAsDone is false, te task is marked as undone.
     *
     * @param index
     * @param isMarkAsDone
     */
    public ToggleMarkTaskCommand(int index, boolean isMarkAsDone) {
        this.index = index;
        this.isMarkAsDone = isMarkAsDone;
    }
    @Override
    public void execute(TaskList taskList, Ui u, Storage storage) throws TreeBotException {
        if (!isMarkAsDone) {
            taskList.unmarkTask(index);
        } else {
            taskList.markTask(index);
        }

        try {
            storage.saveTasks(taskList.getArrayListCopy());
        } catch (IOException e) {
            throw new TreeBotException(e.getMessage());
        }
    }
}
