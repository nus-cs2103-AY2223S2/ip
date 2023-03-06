package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Class for Event, a subclass of Task
 */
public class Event extends Task{
    private final String TASK_TYPE = "[E]";
    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Constructor for Event
     * @param description
     * @param LocalDate startTime
     * @param LocalDate endTime
     */
    public Event(String description, String startTime, String endTime) {
        super(description);

        this.startTime = LocalDate.parse(startTime);
        this.endTime = LocalDate.parse(endTime);
    }

    /**
     * toString() class for Event
     * @return String of description of Event, as well as
     * Localized Date of startTime and
     * Localized Date of endTime
     */
    @Override
    public String toString() {
        return TASK_TYPE + super.toString() + " (from: "
                + DateTimeFormatter.ofLocalizedDate((FormatStyle.FULL)).format(this.startTime) + " to: "
                + DateTimeFormatter.ofLocalizedDate((FormatStyle.FULL)).format(this.endTime) + ")";
    }
}
