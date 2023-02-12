package tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected String deadline;

    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructor for Deadline.
     *
     * @param description The description of the deadline.
     * @param deadline The date and time of the deadline.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        handleDateAndTime();
    }

    /**
     * Returns the date and time of the deadline.
     *
     * @return The date and time of the deadline.
     */
    public void handleDateAndTime() {
        if (deadline.contains(" ")) {
            LocalDate newDate = LocalDate.parse(deadline.split(" ")[0]);
            LocalTime newTime = LocalTime.parse(deadline.split(" ")[1]);
            this.date = newDate;
            this.time = newTime;
        } else {
            LocalDate newDate = LocalDate.parse(deadline);
            this.date = newDate;
        }
    }

    /**
     * Returns the date and time of the deadline.
     *
     * @return The date and time of the deadline.
     */
    public String printDateAndTime() {
        if (!deadline.contains(" ")) {
            return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ", " + time.getHour() + time.getMinute();
        }
    }

    /**
     * Returns the date of the deadline.
     *
     * @return The date of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.description + " (by: " + printDateAndTime() + ")";
    }

    /**
     * Returns the date and time of the deadline.
     *
     * @return The date and time of the deadline.
     */
    @Override
    public String writeToFile() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.description + " (by: " + deadline + ")";
    }
}
