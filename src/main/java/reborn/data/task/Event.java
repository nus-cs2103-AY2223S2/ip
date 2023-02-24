package reborn.data.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents an event task.
 */
public class Event extends Task {

    protected LocalDate fromDate = null;
    protected LocalTime fromTime = null;
    protected LocalDate toDate = null;
    protected LocalTime toTime = null;

    /**
     * Constructor of event task.
     *
     * @param description Description of task.
     * @param from Start of event.
     * @param to End of event.
     */
    public Event(String description, String from, String to) {
        super(description);
        // eg. 2019-12-01 10:15
        String[] fromStrs = from.split(" ");
        this.fromDate = LocalDate.parse(fromStrs[0]);
        if (fromStrs.length == 2) {
            this.fromTime = LocalTime.parse(fromStrs[1]);
        }

        String[] toStrs = to.split(" ");
        this.toDate = LocalDate.parse(toStrs[0]);
        if (toStrs.length == 2) {
            this.toTime = LocalTime.parse(toStrs[1]);
        }
    }

    /**
     * String representation of task for storage.
     *
     * @return String of task to store.
     */
    @Override
    public String storageStr() {
        return "E | " + super.getStatusValue() + " | " + super.description
                + " | " + this.fromDate + (this.fromTime != null ? " " + this.fromTime : "")
                + " | " + this.toDate + (this.toTime != null ? " " + this.toTime : "");
    }

    /**
     * String representation of task.
     *
     * @return String of task.
     */
    @Override
    public String toString() {
        String result = "[E]" + super.toString() + " (from: " + fromDate;
        if (fromTime != null) {
            result = result + " " + fromTime.toString();
        }
        result = result + " to: " + toDate;
        if (toTime != null) {
            result = result + " " + toTime.toString();
        }
        return result += ")";
    }
}
