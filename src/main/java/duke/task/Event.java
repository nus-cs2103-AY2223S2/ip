package duke.task;

import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    String taskDescription;
    LocalDate startDate;
    LocalDate endDate;

    public Event(String taskString, LocalDate startDateInput, LocalDate endDateInput) {
        super(taskString.substring(6, taskString.indexOf("/") - 1));

        taskDescription = taskString.substring(6, taskString.indexOf("/") - 1);
        startDate = startDateInput;
        endDate = endDateInput;
    }

    @Override
    public String getTask() {
        return this.taskDescription;
    }

    public String getTimeline() {
        return this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to " + this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + this.getTimeline() + ")";
    }
}
