package duke.task;

import java.time.LocalDateTime;

/**
 * A task with start and end dates and times.
 */
public class Event extends Task {

    protected String ddmmyyyyFrom;
    protected String hhmmFrom;
    protected LocalDateTime datetimeFrom;

    protected String ddmmyyyyTo;
    protected String hhmmTo;
    protected LocalDateTime datetimeTo;

    /**
     * Creates a new Event object.
     * @param desc A description of the task.
     * @param ddmmyyyyFrom The date the event starts.
     * @param hhmmFrom The time the event starts.
     * @param ddmmyyyyTo The date the event ends.
     * @param hhmmTo The time the event ends.
     */
    public Event(String desc, String ddmmyyyyFrom, String hhmmFrom, String ddmmyyyyTo, String hhmmTo) {
        super(desc);
        this.ddmmyyyyFrom = ddmmyyyyFrom;
        this.hhmmFrom = hhmmFrom;
        this.ddmmyyyyTo = ddmmyyyyTo;
        this.hhmmTo = hhmmTo;
        String[] dateFrom = ddmmyyyyFrom.split("/");
        String[] dateTo = ddmmyyyyTo.split("/");
        this.datetimeFrom = LocalDateTime.of(Integer.parseInt(dateFrom[2]),
                Integer.parseInt(dateFrom[1]), Integer.parseInt(dateFrom[0]),
                Integer.parseInt(hhmmFrom.substring(0, 2)), Integer.parseInt(hhmmFrom.substring(2)));
        this.datetimeTo = LocalDateTime.of(Integer.parseInt(dateTo[2]),
                Integer.parseInt(dateTo[1]), Integer.parseInt(dateTo[0]),
                Integer.parseInt(hhmmTo.substring(0, 2)), Integer.parseInt(hhmmTo.substring(2)));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s %s to: %s %s)",
                dateFormatter.format(datetimeFrom), timeFormatter.format(datetimeFrom),
                dateFormatter.format(datetimeTo), timeFormatter.format(datetimeTo));
    }

    /**
     * Gets the event details to save in data/tasks.txt.
     * @return Event details.
     */
    public String getDetailsToSave() {
        return String.format("event %s %s %s %s %s\n%s", isDone, ddmmyyyyFrom, hhmmFrom, ddmmyyyyTo, hhmmTo, desc);
    }

}
