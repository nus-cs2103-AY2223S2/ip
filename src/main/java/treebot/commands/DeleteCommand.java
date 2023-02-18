package treebot.commands;

import treebot.interfaces.IUndoable;
import treebot.tasks.Task;


import java.io.IOException;

/**
 * Represents a <code>Command</code> that when executed deletes a task at a particular index.
 */
public class DeleteCommand extends Command implements IUndoable {
    private int index;
    private Task deletedTask;

    /**
     * Constructs <code>DeleteCommand</code>.
     * @param index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }


    @Override
    public String execute() {

        this.deletedTask = taskList.deleteTask(index);

        try {
            storage.saveTasks(taskList.getArrayListCopy());
            return this.toResultString();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    public void undo() {
        this.taskList.addTask(deletedTask, index);
    }

    @Override
    String toResultString() {
        assert deletedTask != null : "Deleted task should have been assigned";
        assert taskList != null : "taskList should have been assigned";
        String opening = "Tree has removed the following task: \n";
        String subject = deletedTask.toString();
        String closing = "\nNow you have " + this.taskList.getSize() + " tasks remaining.";
        return opening + subject + closing;
    }
}
