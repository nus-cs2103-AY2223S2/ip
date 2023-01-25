package peppa.commands;

import java.time.LocalDate;

import peppa.Event;
import peppa.PeppaException;
import peppa.Storage;
import peppa.Task;
import peppa.TaskList;
import peppa.Ui;

public class EventCommand implements Command {
    public static final String COMMAND_WORD = "event";
    public static final int DESC_INDEX = 6;
    public static final String ABBREVIATION = "E";
    private String taskDescription;
    private LocalDate startDate;
    private LocalDate endDate;

    public EventCommand() {};
    public EventCommand(String taskDescription, LocalDate startDate, LocalDate endDate) {
        this.taskDescription = taskDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static String getParameterValue(String command, int start, int end) throws PeppaException {
        if (end < start) {
            throw new PeppaException("Boink! Peppa could not process the request. "
                    + "Please ensure that the input is formatted correctly and try again.");
        } else {
            return command.substring(start, end);
        }
    }

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
    public void execute(TaskList taskList, Ui screen, Storage storage) {
        Task task = new Event(taskDescription, startDate, endDate);
        taskList.addTask(task);
        Ui.displayAddTaskMessage(task);
        Ui.displayTaskSummary(taskList);
        storage.saveChanges(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
