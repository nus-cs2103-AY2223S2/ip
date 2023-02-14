package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command that search for tasks with keywords.
 *
 * @author wz2k
 */
public class FindTaskWithTextCommand extends Command {
    /** The list of task maintained by the chatbot */
    private TaskList taskList;

    /**
     * Creates a command for listing tasks that matches the keywords.
     *
     * @param commandMessage User's input.
     * @param taskList List of tasks.
     */
    public FindTaskWithTextCommand(String commandMessage, TaskList taskList) {
        super(commandMessage);
        this.taskList = taskList;
    }

    /**
     * Lists the matching tasks for the user and returns if the conversation with
     * the chatbot has ended.
     *
     * @return Taskbot reply with the found tasks.
     */
    @Override
    public String execute() {
        String searchString = commandMessage.split(" ", 2)[1];
        String noTaskMessage = "No task stored.";
        int listSize = taskList.getSize();

        if (listSize == 0) {
            return noTaskMessage;
        } else {
            return listMatchingTasks(searchString.trim(), listSize);
        }
    }

    /**
     * Finds matching tasks and return them as a list.
     *
     * @param searchString Keyword for the search.
     * @param listSize Total number of tasks in list.
     * @return List of matching tasks.
     */
    public String listMatchingTasks(String searchString, int listSize) {
        StringBuilder reply = new StringBuilder("The following tasks matches your query:");
        String noMatchMessage = "No matches found.";
        int count = 0;

        for (int i = 1; i <= listSize; i++) {
            Task task = taskList.getTask(i);

            if (!task.hasSubstring(searchString)) {
                continue;
            }

            reply.append("\n").append(i).append(". ").append(task);
            count++;
        }

        return count == 0 ? noMatchMessage : reply.toString();
    }

    /**
     * Checks if the input arguments are valid.
     *
     * @throws DukeException If arguments are not valid.
     */
    @Override
    public void checkArguments() throws DukeException {
        String args = commandMessage.substring(4).trim();
        if (args.length() == 0) {
            String emptyArgumentsMessage = "find arguments cannot be empty";
            throw new DukeException(emptyArgumentsMessage);
        }
    }
}
