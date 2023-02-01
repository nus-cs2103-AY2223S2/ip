package dudu.task;

import dudu.exception.InvalidCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task class for deadline
 */
public class Deadline extends Task {
    private final String desc;
    private boolean isDone;
    private LocalDate by;

    /**
     * Constructor for deadline task.
     * @param desc Description of the task.
     * @param by The deadline date of the task.
     * @throws InvalidCommandException If the format of the date is wrong
     */
    public Deadline(String desc, String by) throws InvalidCommandException {
        this(desc, by, false);
    }

    /**
     * Constructor for deadline task, with completion option.
     * @param desc Description of the task.
     * @param by The deadline date of the task.
     * @param isDone The completion status of the task.
     */
    public Deadline(String desc, String by, boolean isDone) throws InvalidCommandException {
        super(desc, isDone);
        this.desc = desc;
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException ex) {
            throw new InvalidCommandException(ex.getMessage(),
                    "The date format Wrong! Please follow following format: yyyy-MM-dd");
        }
    }

    @Override
    public String getType() {
        return "Deadline";
    }

    @Override
    public String getStatus() {
        return isDone() ? "1" : "0";
    }

    @Override
    public String getDescription() {
        return desc + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
