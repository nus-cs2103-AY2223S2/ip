package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for tasks with specific timeline, from a date to another date
 */
public class Event extends Task {
    private String taskDescription;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructor for Event task
     * @param taskString user taskString input
     * @param startDateInput start date of event
     * @param endDateInput end date of event
     */
    public Event(String taskString, LocalDate startDateInput, LocalDate endDateInput) {
        super(taskString.substring(6, taskString.indexOf("/from") - 1));

        taskDescription = taskString.substring(6, taskString.indexOf("/from") - 1);
        startDate = startDateInput;
        endDate = endDateInput;
    }

    @Override
    public String getTask() {
        return this.taskDescription;
    }

    @Override
    public String fullDetails() {
        return this.taskDescription + " " + this.getTimeline();
    }

    /**
     * Get the timeline of event in a specific format ( start date to end date )
     * @return String of a formatted timeline
     */
    public String getTimeline() {
        return this.startDate.format(
                DateTimeFormatter.ofPattern("MMM d yyyy")) + " to "
                + this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + this.getTimeline() + ")";
    }
}
