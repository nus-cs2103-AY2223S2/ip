package task;

import java.text.ParseException;
import java.util.Date;

/**
 * Deadline is a class that extends the Task class and adds a deadline property.
 * The class takes a description and a string representation of the deadline as inputs and sets the deadline property.
 *
 * @author Tan Yan-Hao Joshua
 */
public class Deadline extends Task {

    /**
     * The deadline date and time of the Deadline task.
     */
    private final Date deadline;

    /**
     * Constructor for Deadline that takes a description and a string representation of the deadline as inputs.
     * The constructor uses the super class's readFormat to parse the deadline string into a Date object.
     * The constructor throws a ParseException if the deadline string cannot be parsed into a Date object.
     *
     * @param description The description of the task.
     * @param deadline The string representation of the deadline.
     * @throws ParseException If the deadline string cannot be parsed into a Date object.
     */
    public Deadline(String description, String deadline) throws ParseException {
        super(description);
        this.deadline = super.getReadFormat().parse(deadline);
    }

    /**
     * Overridden save() method for Deadline.
     * The method returns a string representation of the task with deadline in the Read Format.
     *
     * @return A string representation of the task in a specific format.
     */
    @Override
    public String save() {
        return "[D]" +
                super.toString() + " (by: " +
                super.getReadFormat().format(this.deadline) +
                ")";
    }

    /**
     * Overridden toString() method for Deadline.
     * The method returns a string representation of the task with deadline in the Write Format.
     *
     * @return A string representation of the task in a specific format.
     */
    @Override
    public String toString() {
        return "[D]" +
                super.toString() + " (by: " +
                super.getWriteFormat().format(this.deadline) +
                ")";
    }
}
