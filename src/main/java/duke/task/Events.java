package duke.task;

import java.time.LocalDateTime;

/**
 * {@code Events} class encapsulates an event Task.
 */
public class Events extends Task{
    /**
     * {@code LocalDateTime} object that stores date and time
     * of start of event
     */
    protected LocalDateTime from;

    /**
     * {@code LocalDateTime} object that stores date and time
     * of end of event
     */
    protected LocalDateTime to;

    /**
     * Constructor method for {@code Events} class
     * @param description description of Events Task
     * @param from String representation of date and time of start of event
     * @param to String representation of date and time of start of event
     */
    public Events(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    /**
     * Provides string representation of {@code Events} object
     * @return string representation of Event
     */
    @Override
    public String toString() {
        return "E" + " | " + super.toString() + " (from: " + this.from
                + "to: " + this.to + ")";
    }

    /**
     * converts String to {@code LocalDateTime} object with day and time of
     * start and end of event
     * @param period String representation of day and time of start and end of event
     * @return new {@code LocalDateTime} object containing day and time
     */
    private LocalDateTime parseDateTime(String period) {
        String[] dateAndTime = period.split(" ");
        String[] dayMonthYear = dateAndTime[0].split("/");
        int hour = Integer.valueOf(dateAndTime[1])/100;
        int minute = Integer.valueOf(dateAndTime[1])%100;
        int[] ddMMYY = new int[3];
        for (int i = 0; i < dayMonthYear.length;i++){
            ddMMYY[i] = Integer.valueOf(dayMonthYear[i]);
        }

        return LocalDateTime.of(ddMMYY[2], ddMMYY[1], ddMMYY[0], hour, minute);
    }
}
