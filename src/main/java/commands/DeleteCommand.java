package commands;

import exception.TreeBotException;
import tasks.Task;
import tasks.TaskList;
import utils.Storage;
import utils.Ui;

import java.io.IOException;

public class DeleteCommand extends Command{
    private int index;
    private Task deletedTask;
    private TaskList taskList;

    /**
     * Returns a Command that when executed deletes a task at a given index of TaskList.
     * @param index
     */
    public DeleteCommand(int index) {
        this.index = index;
    }


    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {

        this.deletedTask = taskList.deleteTask(index);
        this.taskList = taskList;

        try {
            storage.saveTasks(taskList.getArrayListCopy());
            return this.toResultString();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    String toResultString() {
        String opening = "Tree has removed the following task: \n";
        String subject = deletedTask.toString();
        String closing = "\nNow you have " + this.taskList.getSize() + " tasks remaining.";
        return opening + subject + closing;
    }
}
