package wessy.task;

import java.time.LocalDateTime;

/**
 * This Deadline class inherits from its parent class, Task, and it encapsulates
 * the information and operations required while handling a "deadline" task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs an instance of Deadline by specifying the task description,
     * the specified time by that this Deadline task needs to be done and
     * whether the task has been done.
     *
     * @param description The specified task description.
     * @param by The specified time by that this Deadline task needs to be done.
     * @param isDone The status of whether the task has been done.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone){
        super(description, isDone);
        this.by=by;
    }

    /**
     * Constructs an instance of Deadline by specifying only the task
     * description and the specified time by that this Deadline task needs to be
     * done. The status of this Deadline task is by default set as not done when
     * initialised.
     *
     * @param description The specified task description.
     * @param by The specified time by that this Deadline task needs to be done.
     */
    public Deadline(String description, LocalDateTime by) {
        this(description, by, false);
    }

    /**
     * Overrides the toString method of the parent class, Task. Returns the
     * String representation of this Deadline object by providing more details
     * on the specified time by that this Deadline task needs to be done, on top
     * of the String representation returned by its parent's toString method.
     *
     * @return The String representation of this Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + timeToString(by) + ")";
    }

    /**
     * A helper function that takes in a LocalDateTime object and converts it
     * into a useful String representation.
     *
     * @param dateTime The LocalDateTime object that this method takes in.
     * @return A useful String representation of the inputted LocalDateTime
     * object.
     */
    private static String timeToString(LocalDateTime dateTime) {
        String str = dateTime.toString();
        if (str.substring(11).equals("12:34:56")) {
            return str.substring(0, 10);
        }
        return str.substring(0, 10) + " " + str.substring(11, 16);
    }

    /**
     * Converts this Deadline object into a String representation that will be
     * used while saving this task in a .txt file to the Wessy's storage.
     *
     * @param separator A string that indicates the partition between the
     *                  different fields of a Deadline object.
     * @return A String representation that will be used while saving this task
     * to the Wessy's storage.
     */
    @Override
    public String saveAsStr(String separator) {
        return "D" + super.saveAsStr(separator) + separator + by + "\n";
    }
}