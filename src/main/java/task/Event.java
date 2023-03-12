package task;

import java.text.ParseException;
import java.util.Date;
/**
 * Event is a class that extends the Task class and adds start and end properties.
 * The class takes a description, a string representation of the start time, and a string representation
 * of the end time as inputs and sets the start and end properties.
 *
 * @author Tan Yan-Hao Joshua
 */
public class Event extends Task {
    /**
     * The start date and time of the Event task.
     */
    private final Date start;
    /**
     * The end date and time of the Event task.
     */
    private final Date end;
    /**
     * Constructor for Event that takes a description, a string representation of the start time,
     * and a string representation of the end time as inputs.
     * The constructor uses the super class's readFormat to parse the start and end strings into Date objects.
     * The constructor throws a ParseException if the start or end strings cannot be parsed into Date objects.
     *
     * @param description The description of the task.
     * @param start The string representation of the start time.
     * @param end The string representation of the end time.
     * @throws ParseException If the start or end strings cannot be parsed into Date objects.
     */
    public Event(String description, String start, String end) throws ParseException {
        super(description);
        this.start = super.getReadFormat().parse(start);
        this.end = super.getReadFormat().parse(end);
    }
    /**
     * Overridden save() method for Event.
     * The method returns a string representation of the task with start and end in the Read Format.
     *
     * @return A string representation of the task in a specific format.
     */
    @Override
    public String save() {
        return "[E]"
                + super.toString()
                + " (from: "
                + super.getReadFormat().format(this.start)
                + " to: "
                + super.getReadFormat().format(this.end)
                + ")";
    }
    /**
     * Overridden toString() method for Event.
     * The method returns a string representation of the task with start and end in the Read Format.
     *
     * @return A string representation of the task in a specific format.
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: "
                + super.getWriteFormat().format(this.start)
                + " to: "
                + super.getWriteFormat().format(this.end)
                + ")";
    }
}
