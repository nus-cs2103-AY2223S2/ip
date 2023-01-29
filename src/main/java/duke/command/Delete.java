package duke.command;

import duke.task.TaskList;

/**
 * This class implements the delete command.
 */
public class Delete extends Commands{
    public Delete(String str) {
        this.setCommandStorage(str);
    }

    /**
     * This method removes the selected task from the task list, and decrement the task count by 1.
     * It then print the message to show the user the task deleted.
     * @param list
     */
    @Override
    public void execute(TaskList list) {
        String content = this.getCommandStorage();
        int taskNumber = Character.getNumericValue(content.charAt(content.length() - 1));
        taskNumber -= 1;
        System.out.println("Noted. I've removed this task:\n" + list.deleteTask(taskNumber));
    }
}
