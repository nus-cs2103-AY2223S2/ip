package duke.command;

import duke.task.TaskList;
import duke.UI;

/**
 * Implements the delete command.
 */
public class Delete extends Commands{
    public Delete(String str) {
        this.setCommandStorage(str);
    }

    /**
     * Removes the selected task from the task list, and decrement the task count by 1.
     * Print the message to show the user the task deleted.
     * @param list The list of tasks from which task needs to be deleted.
     */
    @Override
    public void execute(TaskList list) {
        String content = this.getCommandStorage();
        int taskNumber = Character.getNumericValue(content.charAt(content.length() - 1));
        taskNumber -= 1;
        String deleted = list.deleteTask(taskNumber);
        UI.removeUI(deleted);
    }
}
