package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event Task.
 */
public class Event extends Task {

    private LocalDate startTimeParsed;
    private LocalDate endTimeParsed;

    /**
     * Constructor for Event.
     *
     * @param id the id associated with this Task
     * @param task the event description
     * @param startTime starting time of event
     * @param endTime ending time of event
     */
    public Event(int id, String task, LocalDate startTime, LocalDate endTime) {
        super(id, task);
        this.startTimeParsed = startTime;
        this.endTimeParsed = endTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String printTask() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

        return this.isDone()
                ? "[E][x] " + this.getTask() + " (Start: "
                        + this.startTimeParsed.format(formatter)
                        + " | End: "
                        + endTimeParsed.format(formatter) + ")"
                : "[E][ ] " + this.getTask() + " (Start: "
                        + this.startTimeParsed.format(formatter)
                        + " | End: "
                        + this.endTimeParsed.format(formatter) + ")";
    }
}
