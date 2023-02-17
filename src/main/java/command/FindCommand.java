package command;

import duke.DukeException;

import task.TaskList;


/**
 * A command for finding a class
 */
public class FindCommand extends TaskCommand {
    private static final int NUM_COMPONENTS = 2;

    /**
     * Default constructor
     * @param command the user-input command
     * @param doesPrint whether to print messages
     * @throws DukeException when parsing errors occur
     */
    public FindCommand(String command, boolean doesPrint) throws DukeException {
        super(command, doesPrint, NUM_COMPONENTS);
    }

    /**
     * Execute the find task
     * @param taskList the list of tasks
     * @return the string representation of tasks match the requirement
     * @throws DukeException when the task cannot be found or the command is incomplete
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        String[] stringsToSearch = getCommandContent(command).split(STRING_SPACE);
        String taskListString = "Here are the fully matched tasks in your list:\n"
                + taskList
                .getFullyMatchedTaskNames(stringsToSearch)
                .getTaskListString(true);
        taskListString = taskListString
                + "\n\nAdditionally, here are some partially matched tasks:\n"
                + taskList
                .getOnlyPartiallyMatchedTaskNames(stringsToSearch)
                .getTaskListString(true);
        return taskListString;
    }
}
