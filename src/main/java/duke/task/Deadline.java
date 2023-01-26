package duke.task;

import duke.Formatter;

import java.time.LocalDate;

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
                super.toString(), Formatter.formatDatePrint(this.end));
        return toPrint;
    }

    /**
     * Returns a formatted string representation of this task for storage.
     *
     * @return A string representation of this task.
     */
    @Override
    public String formatStore() {
        return ("D | " + super.formatStore()
                + String.format(" | %s", Formatter.formatDateStore(this.end)));
    }
}
