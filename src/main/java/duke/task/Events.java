package duke.task;

import java.time.LocalDateTime;

/**
 * {@code Events} class encapsulates an event Task.
 */
public class Events extends Task {
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
        String eventString = "E" + " | " + super.toString()
                + " (from: " + this.from
                + "to: " + this.to + ")";
        if (super.tag.toString().isEmpty()) {
            eventString += " | Tag: " + super.tag.toString();
        }
        return eventString;
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

        //Checks if 2 strings: date and time were given
        assert dateAndTime.length == 2;
        //Checks if 3 strings: day, month and year were given
        assert dayMonthYear.length == 3;

        int hour = Integer.parseInt(dateAndTime[1]) / 100;
        int minute = Integer.parseInt(dateAndTime[1]) % 100;
        int[] dateNumbers = new int[3];
        for (int i = 0; i < dayMonthYear.length; i++) {
            dateNumbers[i] = Integer.parseInt(dayMonthYear[i]);
        }

        return LocalDateTime.of(dateNumbers[2], dateNumbers[1], dateNumbers[0], hour, minute);
    }
}
