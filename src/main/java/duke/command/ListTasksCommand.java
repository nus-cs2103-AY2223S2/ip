package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command that lists the user's tasks.
 *
 * @author wz2k
 */
public class ListTasksCommand extends Command {
    /** The list of task maintained by the chatbot */
    private TaskList taskList;

    /**
     * Creates a command for listing the user's tasks.
     *
     * @param commandMessage User's input.
     * @param taskList List of tasks.
     */
    public ListTasksCommand(String commandMessage, TaskList taskList) {
        super(commandMessage);
        this.taskList = taskList;
    }

    /**
     * Lists the tasks for the user and returns the reply with the list
     * of found tasks
     *
     * @return The list of found tasks.
     */
    @Override
    public String execute() {
        int listSize = taskList.getSize();
        String noTaskMessage = "No task stored.";

        if (listSize == 0) {
            return noTaskMessage;
        } else {
            return listTasks(listSize);
        }
    }

    /**
     * Lists all tasks.
     *
     * @param listSize Total number of tasks in list.
     * @return List of all the tasks.
     */
    public String listTasks(int listSize) {
        StringBuilder reply = new StringBuilder("Total tasks: " + taskList.getSize());

        for (int i = 1; i <= listSize; i++) {
            Task task = taskList.getTask(i);
            reply.append("\n").append(i).append(". ").append(task);
        }

        return reply.toString();
    }

    /**
     * Checks if the input arguments are valid.
     *
     * @throws DukeException If arguments are not valid.
     */
    @Override
    public void checkArguments() throws DukeException {
        String args = commandMessage.substring(4).trim();
        if (args.length() != 0) {
            String invalidArgumentsMessage = "Invalid input";
            throw new DukeException(invalidArgumentsMessage);
        }
    }
}
