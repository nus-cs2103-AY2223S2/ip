package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Deadline class that inherits from Task.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter formatOfDate = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
    private final LocalDate endDate;

    /**
     * Constructs Deadline.
     *
     * @param name Description of the task.
     * @param endDate Deadline of the task.
     */
    public Deadline(String name, LocalDate endDate) {
        super(name);
        this.endDate = endDate;
    }

    /**
     * Returns the details of the deadline task to be written in file.
     *
     * @return Details of deadline task.
     */
    public String toText() {

        return "D" + "|" + getNameOfTask() + "|" + (isDone() ? 1 : 0) + "|" + endDate;
    }

    /**
     * Returns the details of the deadline task to be output to user.
     *
     * @return Details of deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatOfDate.format(endDate) + ")";
    }
}
