package peppa.commands;

import java.time.LocalDate;

import peppa.Event;
import peppa.PeppaException;
import peppa.Storage;
import peppa.Task;
import peppa.TaskList;
import peppa.Ui;

/**
 * Represents an event command that adds the event to the tasklist.
 */
public class EventCommand implements Command {
    public static final String COMMAND_WORD = "event";
    public static final int DESC_INDEX = 6;
    public static final String ABBREVIATION = "E";
    private String taskDescription;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructs an event command with the specified task description, start date and end date.
     *
     * @param taskDescription Details about the event to be added.
     * @param startDate Date on which the event commences.
     * @param endDate Date on which the event ends.
     */
    public EventCommand(String taskDescription, LocalDate startDate, LocalDate endDate) {
        this.taskDescription = taskDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the value of the parameter (starting date or ending date).
     *
     * @param command Command given by the user.
     * @param start Index/position of the first character of the parameter.
     * @param end Index/position of the last character of the parameter.
     * @return Parameter Value.
     * @throws PeppaException If end < start
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
     * Returns the position of the specified parameter.
     *
     * @param command Command given by the user.
     * @param param Parameter to search for in the command.
     * @return Parameter Index.
     * @throws PeppaException If parameter is not found.
     */
    public static int getParameterIndex(String command, String param) throws PeppaException {
        int idx = command.indexOf("/" + param);
        if (idx == -1) {
            throw new PeppaException("Boink! Peppa could not process the request. "
                    + "Please ensure that the input is formatted correctly and try again.");
        } else {
            return idx;
        }
    }

    @Override
    public String execute(TaskList taskList, Ui screen, Storage storage) {
        Task task = new Event(taskDescription, startDate, endDate);
        taskList.addTask(task);
        storage.saveChanges(taskList);
        return Ui.getAddTaskMessage(task) + Ui.getTaskSummary(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
