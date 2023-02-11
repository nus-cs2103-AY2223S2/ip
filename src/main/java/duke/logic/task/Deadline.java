package duke.logic.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * Deadline Task with a due date.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected LocalDateTime dueDate;

    /**
     * Constructor for Deadline object.
     *
     * @param description Description of task.
     * @param dueDate Deadline of task in yyyy-MM-dd HH:mm format.
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Constructor for Deadline object.
     *
     * @param description Description of task.
     * @param dueDate Deadline of task in yyyy-MM-dd HH:mm format.
     * @param isDone Whether task is marked complete.
     */
    public Deadline(String description, LocalDateTime dueDate, Boolean isDone) {
        super(description, isDone);
        this.dueDate = dueDate;
    }

    /**
     * Factory method to create Deadline object. String input should contain dueDate
     * in the format /by yyyy-MM-dd HH:mm.
     *
     * @param str String to be processed into Deadline object.
     * @return Deadline object.
     * @throws DukeException If format of input is incorrect.
     */
    public static Deadline create(String str) throws DukeException {
        if (str.length() < 1) {
            throw new DukeException();
        } else {
            String[] text = str.substring(1).split(" /by ");
            if (text.length < 2) {
                throw new DukeException();
            } else {
                return new Deadline(text[0], LocalDateTime.parse(text[1], Deadline.FORMATTER));
            }
        }
    }

    /**
     * Factory method to create Deadline object with boolean input. String input should contain dueDate
     * in the format /by yyyy-MM-dd HH:mm.
     *
     * @param str String to be processed into Deadline object.
     * @param isDone Whether Deadline object should be mark as completed.
     * @return Deadline object.
     * @throws DukeException If format of input is incorrect.
     */
    public static Deadline create(String str, Boolean isDone) throws DukeException {
        if (str.length() < 1) {
            throw new DukeException();
        } else {
            String[] text = str.substring(1).split(" /by ");
            if (text.length < 2) {
                throw new DukeException();
            } else {
                return new Deadline(text[0], LocalDateTime.parse(text[1], Deadline.FORMATTER), isDone);
            }
        }
    }

    public String getDueDate() {
        return this.dueDate.format(FORMATTER);
    }

    public LocalDate getLocalDateDue() {
        return this.dueDate.toLocalDate();
    }

    @Override
    public String getType() {
        return "deadline";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm")) + ")";
    }
}
