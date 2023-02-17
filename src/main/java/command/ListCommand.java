package command;

import duke.DukeException;

import task.TaskList;

/**
 * List commands
 */
public class ListCommand extends Command {
    private static int NUM_COMPONENTS = 1;

    /**
     * Default constructor, saves the command
     *
     * @param command the user-input command
     * @param doesPrint whether to print response message to the console
     * @throws DukeException when the input command cannot be parsed
     */
    public ListCommand(String command, boolean doesPrint) throws DukeException {
        super(command, doesPrint, NUM_COMPONENTS);
    }

    /**
     * Lists all tasks
     *
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
