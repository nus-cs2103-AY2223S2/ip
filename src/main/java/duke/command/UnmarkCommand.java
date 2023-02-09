package duke.command;

import duke.tasklist.Task;
import duke.tasklist.TaskList;

/**
 * Represents the class of command which unmarks the tasks in the taskList.
 */
public class UnmarkCommand {
    private TaskList tasks;

    public UnmarkCommand(TaskList arrList) {
        this.tasks = arrList;
    }

    /**
     * Logic for the unmarking the task.
     * Returns a String representation of the text that is generated
     * upon running the specific unmark command.
     *
     * @param input String representation of what to unmark.
     * @return String that represents the program output.
     */
    public String execute(String input) {
        int index = Integer.parseInt(input);
        Task toUnmark = this.tasks.get(index);
        this.tasks.setToUnmark(index);
        String toReturn = "OK, I've marked this task as not done yet:\n" + toUnmark;
        return toReturn;
    }
}