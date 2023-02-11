package commands;

import exception.TreeBotException;
import interfaces.IUndoable;
import tasks.Task;
import tasks.TaskList;
import utils.Storage;

import java.io.IOException;

public class AddCommand extends Command implements IUndoable {

    private Task task;

    /**
     * Returns a Command that when executed adds a task to the list of tasks.
     * @param task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute() {
        taskList.addTask(task);
        try {
            storage.saveTasks(taskList.getArrayListCopy());
            return toResultString();

        } catch (IOException e) {
            return e.getMessage();
        }

    }

    public void undo() {
        this.taskList.deleteTask(task);
    }


    @Override
    String toResultString() {
        assert task != null : "Task should not be null";
        String opening = "I have added the following task:\n";
        String subject = task.toString();
        String closing = "Now you have " + taskList.getSize() + " tasks in the list";

        return opening + subject + closing;
    }


}
