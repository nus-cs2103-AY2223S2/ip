package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Representation of the Event task
 */
public class PeriodTask extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    protected DateTimeFormatter timeFormat;

    /**
     * Constructor for the Event class
     * @param description description of the task
     * @param from start date
     * @param to end date
     * @param isDone whether the task is completed
     */
    public PeriodTask(String description, LocalDateTime from, LocalDateTime to, Boolean isDone) {
        super(description, 'P', isDone);
        this.from = from;
        this.to = to;
        this.timeFormat = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
    }

    /**
     * Returns a string representation of this event task
     * @return String
     */
    @Override
    public String toString() {
        return "[P]" + super.toString() + " (from:" + from.format(timeFormat) + " to:" + to.format(timeFormat) + ")";
    }

    public static Task parseCommand(String str) throws DukeException {
        String[] detailE = str.split("/from ", 2);
        Pattern pattern = Pattern.compile("(\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2})");
        Matcher matcher = pattern.matcher(str);
        String startDateTime = null;
        String endDateTime = null;
        if (matcher.find()) {
            startDateTime = matcher.group();
            if (matcher.find()) {
                endDateTime = matcher.group();
            } else {
                System.out.println("End date and time not found:");
            }
        } else {
            System.out.println("Start date and time not found");
        }
        if (detailE.length == 1) {
            throw new DukeException("When is the event? (dd-MM-yyyy)");
        }
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime fromTime = LocalDateTime.parse(startDateTime, formatter1);
        LocalDateTime toTime = LocalDateTime.parse(endDateTime, formatter1);
        assert fromTime.isBefore(toTime) : "End date cannot be earlier than start date";
        PeriodTask newP = new PeriodTask(detailE[0], fromTime, toTime, false);
        return newP;
    }

    /**
     * Returns a string representation of what is saved in the database
     * @return String
     */
    @Override
    public String savedAs() {
        return (super.savedAs() + "|" + this.from.format(timeFormat) + "|" + this.to.format(timeFormat));
    }
}