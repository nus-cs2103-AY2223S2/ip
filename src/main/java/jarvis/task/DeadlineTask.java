package jarvis.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jarvis.exception.command.CommandParseException;
import jarvis.exception.command.InvalidParameterException;
import jarvis.exception.command.MissingParameterException;

/**
 * Task class representing a deadline task
 * with 'by' information.
 */
public class DeadlineTask extends Task {
    public static final String ID = "D";

    private final LocalDate deadline;

    /**
     * Constructor for a deadline task, marked as undone.
     *
     * @param description Description of the task.
     * @param deadline Date string of the deadline.
     * @throws CommandParseException If the description or deadline is invalid.
     */
    public DeadlineTask(String description, String deadline) throws CommandParseException {
        this(description, deadline, false);
    }

    /**
     * Constructor for a deadline task.
     *
     * @param description Description of the task.
     * @param deadline Date string of the deadline.
     * @param isDone Whether the task is marked as done.
     * @throws CommandParseException If the description or deadline is invalid.
     */
    public DeadlineTask(String description, String deadline, boolean isDone) throws CommandParseException {
        super(description, isDone);
        if (deadline == null || deadline.isBlank()) {
            throw new MissingParameterException("Missing deadline", "A deadline ('/by ...') is needed.");
        }
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidParameterException("Invalid deadline", "I don't understand the given date.");
        }
    }

    @Override
    public boolean satisfies(TaskFilter filter) {
        if (!super.satisfies(filter)) {
            return false;
        }
        boolean isAfter = filter.getAfterDate() == null || !filter.getAfterDate().isAfter(this.deadline);
        boolean isBefore = filter.getBeforeDate() == null || !filter.getBeforeDate().isBefore(this.deadline);
        return isAfter && isBefore;
    }

    @Override
    public String serialize() {
        String[] data = {ID, String.valueOf(this.isDone()), this.getDescription(), String.valueOf(this.deadline)};
        return String.join(" / ", data);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", ID, super.toString(), this.deadline);
    }
}
