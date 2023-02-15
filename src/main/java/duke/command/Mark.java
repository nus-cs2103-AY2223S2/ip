package duke.command;

import duke.task.TaskList;
import duke.UI.TextOutput;

/**
 * Marks a particular task in the task list as done.
 */
public class Mark extends Command {
    public Mark(String str) {
        this.setCommandStorage(str);
    }

    /**
     * Mark a specified task in the task list as done.
     * Then decrements the task count.
     * @param tasks the list of tasks to execute the command on.
     */
    @Override
    public String execute(TaskList tasks) {
        String content = this.getCommandStorage();
        int taskNumber = Character.getNumericValue(content.charAt(content.length() - 1));
        taskNumber -= 1;
        tasks.markTask(taskNumber, true);
        return TextOutput.makeMarkDoneString(tasks, taskNumber);
    }
}