package wessy.task;

import java.time.LocalDateTime;

/**
 * This Event class inherits from its parent class, Task, and it encapsulates
 * the information and operations required while handling an event.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an instance of Event by specifying the task description, the
     * specified time from which the Event starts, the specified time til which
     * the Event ends and whether the task has been done.
     *
     * @param description The specified task description.
     * @param from The specified time from which the Event starts.
     * @param to The specified time til which the Event ends.
     * @param isDone The status of whether the task has been done.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to,
                 boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an instance of Event by specifying the task description, the
     * specified time from which the Event starts and the specified time til
     * which the Event ends. The status of this Deadline is by default set as
     * not done when initialised.
     *
     * @param description The specified task description.
     * @param from The specified time from which the Event starts.
     * @param to The specified time til which the Event ends.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        this(description, from, to, false);
    }

    /**
     * Overrides the toString method of the parent class, Task. Returns the
     * String representation of this Event object by providing more details on
     * the specified time from that this Event starts and the specified time til
     * which this Event ends, on top of the String representation returned by
     * its parent's toString method.
     *
     * @return The String representation of this Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + toString(from) + " to: " + toString(to) + ")";
    }

    /**
     * A helper function that takes in a LocalDateTime object and converts it
     * into a useful String representation.
     *
     * @param dateTime The LocalDateTime object that this method takes in.
     * @return A useful String representation of the inputted LocalDateTime
     * object.
     */
    public static String toString(LocalDateTime dateTime) {
        String str = dateTime.toString();
        if (str.substring(11).equals("12:34:56")) {
            return str.substring(0, 10);
        }
        return str.substring(0, 10) + " " + str.substring(11, 16);
    }

    /**
     * Converts this Event object into a String representation that will be
     * used while saving this task in a .txt file to the Wessy's storage.
     *
     * @param separator A string that indicates the partition between the
     *                  different fields of an Event object.
     * @return A String representation that will be used while saving this task
     * to the Wessy's storage.
     */
    @Override
    public String saveAsStr(String separator) {
        return "E" + super.saveAsStr(separator) + separator + from + separator + to + "\n";
    }
}
