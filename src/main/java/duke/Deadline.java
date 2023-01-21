package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
/** 
 * <h1>Deadline tasks with date</h1>
 * The Deadline class is a specific subclass of the Task that allows user to
 * add a stipulated end date for their keyed input. By default, it assumes
 * the Deadline task is undone, because it does not make sense for a user to
 * enter a new Deadline task if it is done. However, should the user want to
 * input a past done Deadline task, he is allowed to do so. The user can
 * also toggle the status of the Deadline task as done or undone. Lastly,
 * the Deadline date can be both a day, and a local date.
 * 
 * @author Muhammad Reyaaz
 * @version %I% %G%
 * @since 11
 *
 */
class Deadline extends Task {

    protected String by;     
    /**
    * Sole constructor. (For invocation by subclass
    * constructors, typically implicit)
    */
    Deadline() {

    }
    /**
     * Allows the user to create a Deadline task that is marked as undone.
     * The Deadline task will have methods inherited from the Task class.
     * This means that other classes will be able to check the status of the
     * Deadline task. After all, a Deadline task is just a subclass of a
     * Task class.
     *
     * @param description The name of the Deadline task, which cannot be
     * modified  
     * @param by the duedate that the Deadline task has to be completed 
     */
    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    /**
     * Allows the user to create a Deadline task that is marked as undone.
     * The Deadline task will have methods inherited from the Task class.
     * This means that other classes will be able to check the status of the
     * Deadline task. After all, a Deadline task is just a subclass of a
     * Task class.
     *
     * @param description The name of the Deadline task, which cannot be
     * modified
     * @param by The duedate that the Deadline task has to be completed
     * @param isDone The status of the task, which will be set to done or
     * undone depending on the user input
     */
    Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }
    /**
     * Based on the deadline that the user has keyed in, it will be
     * modified. If the user keyed in a day, it will be maintained as a day.
     * if a user keys in a date in MMM dd YYYY format, it will be cnverted
     * into a String in the respective format
     *
     * @return the String of a date that the user has keyed in
     * @exception DateTimeParseException if the date format is not in MMM dd
     * YYY, for example it is in DD/MM/YYYY, the exception will be caught
     * and returned as the original String keyed in.
     * @see LocalDate
     */
    String localDateParser() {
        try {
            LocalDate date = LocalDate.parse(by);
            return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            return by;
        }
    }
    /**
     *  The status of the Deadline task will be toggled to "X", which
     *  indicates it is marked as done. However, due to immutability, a new
     *  Deadline class is returned, with the exact same description and due
     *  date
     *  
     *  @return the new toggled Deadline status, which is done 
     */
    @Override
    Deadline markAsDone() {
        return new Deadline(getDescription(), by, true);
    }
    /**
     *  The status of the Deadline task will be toggled to " ", which
     *  indicates it is marked as undone. However, due to immutability, a new
     *  Deadline class is returned, with the exact same description and due
     *  date
     *  
     *  @return the new toggled Deadline status, which is undone
     */
    @Override
    Deadline markAsUndone() {
        return new Deadline(getDescription(), by, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + localDateParser() + ")";
    }
}
