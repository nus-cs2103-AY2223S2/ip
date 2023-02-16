package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadlines Task.
 * @author pzhengze
 */
public class Deadlines extends Task {
    /** Storage of the due date of Deadline. */
    private final LocalDateTime dueDate;

    /** String form of dueDate. */
    private final String stringDueDate;

    /**
     * Constructor for Deadlines object where dueDate is a LocalDateTime object.
     * @param description The description of the Task.
     * @param isDone The boolean showing if the Task has been done.
     * @param dueDate The due date of the Task.
     */
    public Deadlines(String description, boolean isDone, LocalDateTime dueDate) {
        super(description, isDone);
        this.dueDate = dueDate;
        this.stringDueDate = dueDate.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
    }

    /**
     * Constructor for Deadlines object where dueDate is a String.
     * @param description The description of the Task.
     * @param isDone The boolean showing if the Task has been done.
     * @param deadline The due date of the Task.
     */
    public Deadlines(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.dueDate = null;
        this.stringDueDate = deadline;
    }

    /**
     * Factory method for Deadlines object where dueDate is a String.
     * @param description The description of the Task.
     * @param isDone The boolean showing if the Task has been done.
     * @param deadline The due date of the Task.
     * @return The Deadline object
     */
    public static Deadlines createDeadlines(String description, boolean isDone, String deadline) {
        // Attempt to interpret the due date as a LocalDateTime object.
        // If exception is thrown, treat deadline as a String.
        // Create Deadlines object and add it into the list.
        // Adds object into the output String.
        try {
            LocalDateTime tempDueDate = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return new Deadlines(description, isDone, tempDueDate);
        } catch (DateTimeParseException dateTimeParseException) {
            return new Deadlines(description, isDone, deadline);
        }
    }

    /**
     * Returns the Deadlines Task in String form.
     * This shows if the Deadlines Task has been done, represented by [X].
     * @return The Deadlines Task in String form.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                stringDueDate);
    }

    /**
     * Returns the Task in String form specific for saving.
     * @return The Task in String form
     */
    @Override
    public String save() {
        if (dueDate == null) {
            return String.format("deadline %s-%s-%s\n",
                    this.description,
                    this.isDone,
                    this.stringDueDate);
        }
        return String.format("deadline %s-%s-%s\n",
                this.description,
                this.isDone,
                this.dueDate.format(DateTimeFormatter.ofPattern(("d/M/yyyy HHmm"))));
    }
}
