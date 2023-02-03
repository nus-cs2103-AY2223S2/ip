package duke.command;

import duke.task.TaskList;
import duke.UI;

/**
 * Marks a particular task in the task list as not done.
 */
public class Unmark extends Commands {
    public Unmark(String str) {
        this.setCommandStorage(str);
    }

    /**
     * Marks a specified task in the task list as not done.
     * Then increments the task count.
     * @param tasks the task list to execute the command on.
     */
    @Override
    public void execute(TaskList tasks) {
        String content = this.getCommandStorage();
        int taskNumber = Character.getNumericValue(content.charAt(content.length() - 1));
        taskNumber -= 1;
        tasks.markTask(taskNumber, false);
        UI.markTaskUndone(tasks,taskNumber);
    }
}