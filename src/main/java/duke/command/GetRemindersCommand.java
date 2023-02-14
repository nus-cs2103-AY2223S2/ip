package duke.command;

import duke.DukeException;
import duke.enums.TaskType;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command that lists tasks that are due today.
 *
 * @author wz2k
 */
public class GetRemindersCommand extends Command {
    /** The list of task maintained by the chatbot */
    private TaskList taskList;

    /**
     * Creates a command for reminding user about tasks that are due today.
     *
     * @param commandMessage User's input.
     * @param taskList List of tasks.
     */
    public GetRemindersCommand(String commandMessage, TaskList taskList) {
        super(commandMessage);
        this.taskList = taskList;
    }

    /**
     * Lists the tasks that are due today and returns the reply with the list
     * of found tasks
     *
     * @return The list of found tasks that are due today.
     */
    @Override
    public String execute() {
        int listSize = taskList.getSize();
        String noTaskMessage = "No task stored. Add deadlines to get reminders.";

        if (listSize == 0) {
            return noTaskMessage;
        } else {
            return getDueTodayTasks(listSize);
        }
    }

    /**
     * Finds tasks that are due today.
     *
     * @param listSize Total number of tasks in list.
     * @return List tasks that are due today.
     */
    public String getDueTodayTasks(int listSize) {
        StringBuilder reply = new StringBuilder("The following tasks are due today:");
        String noDeadlineTodayMessage = "Nothing is due today.";
        int count = 0;

        for (int i = 1; i <= listSize; i++) {
            Task task = taskList.getTask(i);
            TaskType taskType = task.getTaskType();
            boolean isDeadline = taskType == TaskType.DEADLINE;

            if (!isDeadline) {
                continue;
            }

            Deadline deadline = (Deadline) task;

            if (!deadline.isDueToday()) {
                continue;
            }

            reply.append("\n").append(i).append(". ").append(task);
            count++;
        }

        return count == 0 ? noDeadlineTodayMessage : reply.toString();
    }

    /**
     * Checks if the input arguments are valid.
     *
     * @throws DukeException If arguments are not valid.
     */
    @Override
    public void checkArguments() throws DukeException {
        String illegalAccessMessage = "This method should not be called";
        throw new DukeException(illegalAccessMessage);
    }
}
