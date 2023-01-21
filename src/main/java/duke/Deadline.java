package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeParseException;

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
    Deadline() {

    }
    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }
    String localDateParser() {
        try {
            LocalDate date = LocalDate.parse(by);
            return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            return by;
        }
    }
    @Override
    Deadline markAsDone() {
        return new Deadline(getDescription(), by, true);
    }
    @Override
    Deadline markAsUndone() {
        return new Deadline(getDescription(), by, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + localDateParser() + ")";
    }
}
