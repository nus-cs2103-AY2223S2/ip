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
    private String startDateString;
    private String endDateString;

    /**
     * Constructor for Event task
     * @param taskString user taskString input
     * @param startDateInput start date of event
     * @param endDateInput end date of event
     */
    public Event(String taskString, String startDateInput, String endDateInput) {
        super(taskString);

        taskDescription = taskString;
        startDateString = startDateInput;
        startDate = LocalDate.parse(startDateInput);
        endDateString = endDateInput;
        endDate = LocalDate.parse(endDateInput);
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
        return this.getStartDate() + " to " + this.getEndDate();
    }

    public String getStartDate() {
        return this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getEndDate() {
        return this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + this.getTimeline() + ")";
    }

    @Override
    public String toBeSaved() {
        return "E" + " /// " + super.toBeSaved() + " /// " + this.startDateString + " /// " + this.endDateString;
    }
}
