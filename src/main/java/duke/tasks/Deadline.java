package duke.tasks;

import duke.helper.DateTimeParser;

import java.time.LocalDate;

//solution adapted from 2103 website

/**
 * A class that encapsulates deadlines given by the user
 *
 * @author Tan Matthew Simon Castaneda
 * @version CS2103 AY22/23 Semester 2
 */
public class Deadline extends Task {

    protected LocalDate endTime;

    static final String type  = "D";

    public static String getType() {
        return type;
    }

    public LocalDate getEndTime() {
        return this.endTime;
    }

    /**
     * Constructor to create deadline object based on user input.
     *
     * @param name Name of the associated deadline.
     * @param endTime Time that the deadline is supposed to be met.
     */

    public Deadline(String name, String endTime) {
        super(name);
        this.endTime = DateTimeParser.parse(endTime);
    }


    /**
     * Converts a deadline object to its string representation.
     *
     * @return A string representation of the associated deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endTime.toString() + ")";
    }
}
