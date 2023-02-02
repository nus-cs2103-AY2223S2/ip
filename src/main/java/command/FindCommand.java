package command;

import duke.DukeException;

import task.TaskList;


/**
 * A command for finding a class
 */
public class FindCommand extends TaskCommand {
    /**
     * Default constructor
     * @param command the user-input command
     * @param doesPrint whether to print messaegs
     */
    public FindCommand(String command, boolean doesPrint) throws DukeException {
        super(command, doesPrint, 2);
    }

    /**
     * Execute the find task
     * @param taskList the list of tasks
     * @return the string representation of tasks match the requirement
     * @throws DukeException when the task cannot be found or the command is incomplete
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        String stringToSearch = getCommandContent(command);
        String taskListString = "Here are the matching tasks in your list:\n"
                + taskList.getTaskNameContains(stringToSearch).getTaskListString(true);
        return taskListString;
    }
}
