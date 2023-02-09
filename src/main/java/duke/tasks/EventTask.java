package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that is an event.
 * An event will have a start date and end date.
 */
public class EventTask extends Task {
    private String from;
    private String to;
    private LocalDate fromDate;
    private LocalDate toDate;

    public EventTask(String title, String from, String to) {
        super(title);
        this.from = from;
        this.to = to;
        formatIfEventDate(from, to);
        assert this.from != null : "from String in EventTask should not be null";
        assert this.to != null : "to String in EventTask should not be null";
    }


    private void formatIfEventDate(String from, String to) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/uuuu");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd LLL uuuu");
        try {
            LocalDate outputFrom = LocalDate.parse(from, inputFormat);
            LocalDate outputTo = LocalDate.parse(to, inputFormat);
            this.fromDate =  outputFrom;
            this.toDate = outputTo;
            this.from = outputFrom.format(outputFormat);
            this.to = outputTo.format(outputFormat);
        } catch (DateTimeParseException e) {
            this.fromDate = null;
            this.toDate = null;
            this.from = from;
            this.to = to;
        }
    }

    /**
     * Returns a String that contains information
     * on the EventTask object that is used for loading
     * the task into the ToDoList on Duke startup.
     *
     * @return A String that is used for loading the task into Duke on startup.
     */
    @Override
    public String save() {
        String status = this.isDone ? "DONE/+/" : "NOTDONE/+/";
        String fromToSave = this.from;
        String toToSave = this.to;
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("d/M/uuuu");
        if (this.fromDate != null) {
            fromToSave = this.fromDate.format(outputFormat);
        }
        if (this.toDate != null) {
            toToSave = this.toDate.format(outputFormat);
        }
        return "EVENT/+/" + status + this.title + "/+/" + fromToSave + "/+/" + toToSave +"\n";
    }

    /**
     * Returns a String indicating the number of days left to the from date
     * or to date, whichever is earlier, in this EventTask should the number
     * of days left from today to the earlier date be less than or equal
     * to the input number of days, otherwise returns an empty String.
     *
     * @param dayRange The number of days from the from date or to date, whichever
     *                 is earlier, to be compared to.
     * @return A String with the number of days remaining to the from date
     *         or to date, whichever is earlier, if today falls within the specified
     *         number of days in the input from the date, otherwise an empty String.
     */
    @Override
    public String remind(int dayRange) {
        if (this.fromDate == null || this.toDate == null) {
            return "";
        }
        LocalDate today = LocalDate.now();
        int dayLeftFrom = today.until(this.fromDate).getDays();
        int dayLeftTo = today.until(this.toDate).getDays();

        boolean isFromDateOver = dayLeftFrom < 0;
        boolean isFromDateSoon = dayLeftFrom <= dayRange;
        if (!isFromDateOver && isFromDateSoon && !this.isDone) {
            return "[E] " + title + " (Starting in " + dayLeftFrom + " day!)";
        }

        boolean isToDateOver = dayLeftTo < 0;
        boolean isToDateSoon = dayLeftTo <= dayRange;
        if (isFromDateOver && !isToDateOver && isToDateSoon && !this.isDone) {
            return "[E] " + title + " (Ending in " + dayLeftTo + " day!)";
        }
        return "";
    }

    @Override
    public String toString() {
        String status = this.isDone ? "[x] " : "[ ] ";
        return "[E]" + status + this.title + " (from: " + this.from + " to: " + this.to + ")";
    }
}
