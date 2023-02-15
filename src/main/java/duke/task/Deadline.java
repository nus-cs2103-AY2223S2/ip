package duke.task;

import java.time.LocalDate;

import duke.Formatter;

/**
 * Encapsulates the related fields and behavior of a Deadline task,
 * a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    //The deadline given.
    private LocalDate end;

    /**
     * Instantiates Deadline with two arguments given.
     *
     * @param name The name of the task.
     * @param end The end date/time of deadline.
     */
    public Deadline(String name, LocalDate end) {
        super(name);
        this.end = end;
    }

    /**
     * Instantiates deadline with three arguments given.
     *
     * @param name The name of the task.
     * @param end The end date/time of deadline.
     * @param isDone Status of the task.
     */
    public Deadline(String name, LocalDate end, boolean isDone) {
        super(name, isDone);
        this.end = end;
    }

    /**
     * Returns the string with a [D] icon representing this task.
     *
     * @return A string representation of this Deadline task.
     */
    @Override
    public String toString() {
        String toPrint = String.format("[D]%s (by: %s)",
                super.toString(), Formatter.formatDateForPrint(this.end));
        return toPrint;
    }

    /**
     * Returns a formatted string representation of this task for storage.
     *
     * @return A string representation of this task.
     */
    @Override
    public String formatForStorage() {
        return ("D | " + super.formatForStorage()
                + String.format(" | %s", Formatter.formatDateForStorage(this.end)));
    }

    /**
     * Searches for given date in the task description.
     *
     * @param dateToFind The given date to find.
     * @return True if date matches start or end date, false otherwise.
     */
    @Override
    public boolean containDate(LocalDate dateToFind) {
        if (this.end.equals(dateToFind)) {
            return true;
        }
        return false;
    }
}
