package duke;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A subclass of Task containing list of deadline tasks
 */
public class Deadline extends Task {
    final String icon = "[D]";
    private LocalDate date;
    private LocalTime time;

    /**
     * @param details details of deadline task
     * @param date date of the deadline task
     * @param time time of the deadline task
     */
    public Deadline(String details, LocalDate date, LocalTime time) {
        super(details);
        this.date = date;
        this.time = time;
    }

    /**
     * @param details details of deadline task
     * @param date date of the deadline task
     */
    public Deadline(String details, LocalDate date) {
        super(details);
        this.date = date;
    }

    /**
     * @param details details of the task
     */
    public Deadline(String details) {
        super(details);
    }

    /**
     * @return date of the deadline task
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @return the description of the deadline task indicating as a Deadline task
     */
    @Override
    public String toString() {
        if (time != null) {
            String iconWithTime = icon + super.toString() + "(by: " + date + " " + time + ")";
            return iconWithTime;
        } else if (date != null) {
            String iconWithDate = icon + super.toString() + "(by: " + date + ")";
            return iconWithDate;
        } else {
            String iconWithoutTimeDate = icon + super.toString();
            return iconWithoutTimeDate;
        }
    }
}
