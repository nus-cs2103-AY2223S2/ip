package peppa.commands;

import java.time.LocalDateTime;

import peppa.*;

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

    public static String getParameterValue(String command, int start, int end) throws PeppaException {
        if (end < start) {
            throw new PeppaException("Boink! Peppa could not process the request. "
                    + "Please ensure that the input is formatted correctly and try again.");
        } else {
            return command.substring(start, end);
        }
    }

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
    public void execute(TaskList taskList, Ui screen, Storage storage) {
        Task task = new Deadline(taskDescription, deadline);
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
