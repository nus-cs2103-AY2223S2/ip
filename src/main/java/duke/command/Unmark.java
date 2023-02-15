package duke.command;

import duke.task.TaskList;
import duke.UI.TextOutput;

/**
 * Marks a particular task in the task list as not done.
 */
public class Unmark extends Command {
    public Unmark(String str) {
        this.setCommandStorage(str);
    }

    /**
     * Marks a specified task in the task list as not done.
     * <p>
     * Then increments the task count.
     * @param tasks the task list to execute the command on.
     */
    @Override
    public String execute(TaskList tasks) {
        String content = this.getCommandStorage();
        int taskNumber = Character.getNumericValue(content.charAt(content.length() - 1));
        taskNumber -= 1;
        tasks.markTask(taskNumber, false);
        return TextOutput.makeMarkUndoneString(tasks,taskNumber);
    }
}