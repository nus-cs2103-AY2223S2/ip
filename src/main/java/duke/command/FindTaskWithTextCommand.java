package duke.command;

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
        int size = taskList.getSize();
        String searchString = commandMessage.split(" ", 2)[1];

        if (size == 0) {
            return "No task stored.";
        } else {
            StringBuilder reply = new StringBuilder("The following tasks matches your query:");
            int count = 0;

            for (int i = 1; i <= size; i++) {
                Task task = taskList.getTask(i);
                count++;

                if (task.hasSubstring(searchString)) {
                    reply.append("\n").append(count).append(". ").append(task);
                }
            }
            return count == 0 ? "No matches found." : reply.toString();
        }
    }
}
