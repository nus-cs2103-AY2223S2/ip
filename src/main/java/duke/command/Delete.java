package duke.command;

import duke.task.TaskList;
import duke.UI.TextOutput;
import duke.task.Task;

/**
 * Implements the delete command.
 */
public class Delete extends Command {
    public Delete(String str) {
        this.setCommandStorage(str);
    }

    /**
     * Removes the selected task from the task list, and decrement the task count by 1.
     * Print the message to show the user the task deleted.
     * @param list The list of tasks from which task needs to be deleted.
     */
    @Override
    public String execute(TaskList list) {
        String content = this.getCommandStorage();
        assert content != "" : "Content must not be empty";
        int taskNumber = Character.getNumericValue(content.charAt(content.length() - 1));
        assert taskNumber >= 0: "Command argument must contain a task number";
        taskNumber -= 1; //to get to the correct index in array
        Task deletedTask = list.deleteTask(taskNumber);
        return TextOutput.makeDeleteString(deletedTask, list.getTaskCount());
    }
}
