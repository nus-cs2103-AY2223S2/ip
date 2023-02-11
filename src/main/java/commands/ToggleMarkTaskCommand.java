package commands;

import exception.TreeBotException;
import tasks.Task;
import tasks.TaskList;
import utils.Storage;
import utils.Ui;

import java.io.IOException;

public class ToggleMarkTaskCommand extends Command {
    private int index;
    private boolean isMarkAsDone;
    private Task markedTask;

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
    public String execute(TaskList taskList, Ui u, Storage storage) {
        this.markedTask = !isMarkAsDone ? taskList.unmarkTask(index) : taskList.markTask(index);
        try {
            storage.saveTasks(taskList.getArrayListCopy());
            return toResultString();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    String toResultString() {
        String opening = "I have marked the following task as " +
                (isMarkAsDone ? "done" : "not Done yet") +
                ": \n";
        String subject = this.markedTask.toString();

        return opening + subject;

    }
}
