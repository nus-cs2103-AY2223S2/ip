package commands;

import exception.TreeBotException;
import tasks.Task;
import tasks.TaskList;
import utils.Storage;
import utils.Ui;

import java.io.IOException;

public class AddCommand extends Command{

    private Task task;
    private TaskList taskList;

    /**
     * Returns a Command that when executed adds a task to the list of tasks.
     * @param task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        taskList.addTask(task);
        try {
            storage.saveTasks(taskList.getArrayListCopy());
            return toResultString();

        } catch (IOException e) {
            return e.getMessage();
        }

    }

    @Override
    String toResultString() {
        String opening = "I have added the following task:\n";
        String subject = task.toString();
        String closing = "Now you have " + taskList.getSize() + " tasks in the list";

        return opening + subject + closing;
    }


}
