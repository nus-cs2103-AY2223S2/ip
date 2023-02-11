package commands;

import interfaces.IUndoable;
import tasks.Task;


import java.io.IOException;

public class DeleteCommand extends Command implements IUndoable {
    private int index;
    private Task deletedTask;

    /**
     * Returns a Command that when executed deletes a task at a given index of TaskList.
     * @param index
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
