package treebot.commands;

import treebot.interfaces.IUndoable;
import treebot.tasks.Task;

import java.io.IOException;

/**
 * Represents a <code>Command</code> that when executed adds a given task to the list of tasks.
 */
public class AddCommand extends Command implements IUndoable {

    private Task task;

    /**
     * Default constructor.
     * @param task task to be added to task list.
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

    @Override
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
