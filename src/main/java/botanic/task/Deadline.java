package botanic.task;

import java.time.LocalDate;

import botanic.Formatter;

/**
 * Encapsulates the related fields and behavior of a Deadline task,
 * a task that needs to be done before a specific date.
 */
public class Deadline extends Task {
    //The deadline given.
    private LocalDate end;

    /**
     * Instantiates Deadline with two arguments given.
     *
     * @param name The name of the task.
     * @param end The end date of deadline.
     */
    public Deadline(String name, LocalDate end) {
        super(name);
        this.end = end;
    }

    /**
     * Instantiates deadline with three arguments given.
     *
     * @param name The name of the task.
     * @param end The end date of deadline.
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
                super.toString(), Formatter.formatDateForPrint(end));
        return toPrint;
    }

    /**
     * Returns a formatted string representation of this task for storage.
     *
     * @return A string representation of this Deadline task.
     */
    @Override
    public String formatForStorage() {
        return ("D | " + super.formatForStorage()
                + String.format(" | %s", Formatter.formatDateForStorage(end)));
    }

    /**
     * Checks if given date matches with this deadline's end date.
     *
     * @param dateToFind The given date to find.
     * @return True if date given matches with this deadline's end date, false otherwise.
     */
    @Override
    public boolean hasDate(LocalDate dateToFind) {
        if (end.equals(dateToFind)) {
            return true;
        }
        return false;
    }
}
