package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task
 */
public class Deadline extends Task {

    /**
     * The date and time stored.
     */
    protected LocalDateTime by;
    protected DateTimeFormatter timeFormat;

    /**
     * Constructor for the Deadline Class
     * @param description of the task
     * @param by time that the task is due by
     * @param isDone whether the task is done or not
     */
    public Deadline(String description, LocalDateTime by, Boolean isDone) {
        super(description, 'D', isDone);
        this.by = by;
        this.timeFormat = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
    }

    /**
     * Returns a string representation of this deadline task
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by.format(timeFormat) + ")";
    }

    public static Task parseCommand(String str) throws DukeException {
        String[] detailD = str.split(" /by ", 2);
        if (detailD.length == 1) {
            throw new DukeException("When is the deadline?");
        }
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime byTime = LocalDateTime.parse(detailD[1], formatter1);
        Deadline newD = new Deadline(detailD[0], byTime, false);
        return newD;

    }


    /**
     * Returns a string representation of what is saved in the database
     * @return String
     */
    @Override
    public String savedAs() {
        return (super.savedAs() + "|" + this.by.format(timeFormat));
    }
}
