package command;

import duke.DukeException;
import task.TaskList;

/**
 * List commands
 */
public class ListCommand extends Command {

    /**
     * Default constructor, saves the command
     *
     * @param command the user-input command
     */
    public ListCommand(String command, boolean doesPrint) throws DukeException {
        super(command, doesPrint, 1);
    }

    /**
     * Lists all tasks
     * @param taskList the list of tasks
     * @return string representation of the task list
     */
    @ Override
    public String execute(TaskList taskList) {
        String taskListString = "Here are the tasks in your list:\n"
                + taskList.getTaskListString(true);
        return taskListString;
    }
}
