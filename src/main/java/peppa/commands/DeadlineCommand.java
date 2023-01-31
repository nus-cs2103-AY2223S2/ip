package peppa.commands;

import java.time.LocalDateTime;

import peppa.Deadline;
import peppa.PeppaException;
import peppa.Storage;
import peppa.Task;
import peppa.TaskList;
import peppa.Ui;

/**
 * Represents a deadline command that adds the deadline to the tasklist.
 */
public class DeadlineCommand implements Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String ABBREVIATION = "D";
    public static final int DESC_INDEX = 9;
    private String taskDescription;
    private LocalDateTime deadline;

    public DeadlineCommand(String taskDescription, LocalDateTime deadline) {
        this.taskDescription = taskDescription;
        this.deadline = deadline;
    }

    /**
     * Returns the value of the parameter (deadline).
     *
     * @param command Command given by the user.
     * @param start Index/position of the first character of the parameter.
     * @param end Index/position of the last character of the parameter.
     * @return Parameter Value.
     * @throws PeppaException If end < start.
     */

    public static String getParameterValue(String command, int start, int end) throws PeppaException {
        if (end < start) {
            throw new PeppaException("Boink! Peppa could not process the request. "
                    + "Please ensure that the input is formatted correctly and try again.");
        } else {
            return command.substring(start, end);
        }
    }

    /**
     * Returns the position of the parameter "/by".
     *
     * @param command Command given by the user.
     * @return Parameter Index.
     * @throws PeppaException If parameter /by is not found.
     */
    public static int getParameterIndex(String command) throws PeppaException {
        int idx = command.indexOf("/by");
        if (idx == -1) {
            throw new PeppaException("Boink! Peppa could not process the request. "
                    + "Please ensure that the input is formatted correctly and try again.");
        } else {
            return idx;
        }
    }

    @Override
    public String execute(TaskList taskList, Ui screen, Storage storage) {
        Task task = new Deadline(taskDescription, deadline);
        taskList.addTask(task);
        storage.saveChanges(taskList);
        return Ui.getAddTaskMessage(task) + Ui.getTaskSummary(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
