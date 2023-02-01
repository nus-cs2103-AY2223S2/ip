package duke.command;

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
     * @return the list of found tasks.
     */
    @Override
    public String execute() {
        int size = taskList.getSize();

        if (size == 0) {
            return "No task stored.";
        } else {
            StringBuilder reply = new StringBuilder("Total tasks: " + taskList.getSize());
            int count = 0;

            for (int i = 1; i <= size; i++) {
                count++;
                Task task = taskList.getTask(i);
                reply.append("\n").append(count).append(". ").append(task);
            }

            return reply.toString();
        }
    }
}
