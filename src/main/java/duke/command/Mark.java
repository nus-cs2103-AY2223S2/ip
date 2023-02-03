package duke.command;

import duke.task.TaskList;
import duke.UI;

/**
 * This class marks a particular task in the task list as done.
 */
public class Mark extends Commands {
    public Mark(String str) {
        this.setCommandStorage(str);
    }

    /**
     * Mark a specified task in the task list as done.
     * Then decrements the task count.
     * @param tasks the list of tasks to execute the command on.
     */
    @Override
    public void execute(TaskList tasks) {
        String content = this.getCommandStorage();
        int taskNumber = Character.getNumericValue(content.charAt(content.length() - 1));
        taskNumber -= 1;
        tasks.markTask(taskNumber, true);
        UI.markTaskCompleted(tasks, taskNumber);
    }
}