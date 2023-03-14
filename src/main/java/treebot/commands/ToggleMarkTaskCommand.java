package treebot.commands;

import treebot.interfaces.IUndoable;
import treebot.tasks.Task;

import java.io.IOException;

/**
 * Represents a <code>Command</code> that when executed marks the task at the given index
 * as done or undone.
 */
public class ToggleMarkTaskCommand extends Command implements IUndoable {
    private int index;
    private boolean isMarkAsDone;
    private Task markedTask;

    /**
     * Returns a Command that when executed marks the task in the TaskList as done or not done.
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
    public String execute() {

        try {
            this.markedTask = !isMarkAsDone
                    ? taskList.unmarkTask(index)
                    : taskList.markTask(index);
        } catch (IndexOutOfBoundsException e) {
            return "Given index is out of range of taskList!!";
        }


        try {
            storage.saveTasks(taskList.getArrayListCopy());
            return toResultString();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public void undo() {
        this.markedTask = !isMarkAsDone
                ?  taskList.markTask(index)
                : taskList.unmarkTask(index);
    }

    @Override
    String toResultString() {
        String opening = "I have marked the following task as " +
                (isMarkAsDone ? "done" : "not Done yet") + ": \n";
        assert markedTask != null : "Marked task should have been assigned";
        String subject = this.markedTask.toString();

        return opening + subject;

    }
}
