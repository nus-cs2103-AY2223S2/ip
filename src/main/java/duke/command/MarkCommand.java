package duke.command;

import duke.tasklist.Task;
import duke.tasklist.TaskList;

/**
 * Represents the class of command which marks the tasks in the taskList.
 */
public class MarkCommand {
    private TaskList tasks;

    public MarkCommand(TaskList arrList) {
        this.tasks = arrList;
    }

    /**
     * Logic for the marking the task.
     * Returns a String representation of the text that is generated
     * upon running the specific mark command.
     *
     * @param input String representation of what to mark.
     * @return String that represents the program output.
     */
    public String execute(String input) {
        int index = Integer.parseInt(input);
        Task toUnmark = this.tasks.get(index);
        this.tasks.setToMark(index);
        String toReturn = "Nice! I've marked this task as done:\n" + toUnmark;
        return toReturn;
    }
}
