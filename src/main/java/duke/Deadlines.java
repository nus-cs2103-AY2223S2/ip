package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task that has a date it should be done by.
 */
public class Deadlines extends Task {

    protected LocalDateTime dueDate;

    public Deadlines(String name, String dueDate) {
        super(name, "D");
        this.dueDate = makeDateTime(dueDate);
    }

    public Deadlines(String name, String dueDate, boolean isDone) {
        super(name, "D", isDone);
        this.dueDate = makeDateTime(dueDate);
    }

    /**
     * Adds on the due date to the <code>description()</code> method in
     * <code>Task</code>.
     * @return a <code>String</code> in the format to be output to the user
     */
    @Override
    public String getDescription() {
        return String.format("%s (by: %s)", super.getDescription(),
                formatDateTime(this.dueDate));
    }

    /**
     * Adds on the due date to the <code>formatDescription()</code> method in
     * <code>Task</code> class.
     * @return a <code>String</code> to be added into the task list representing
     * this <code>Deadline</code> object.
     */
    @Override
    public String formatDescription() {
        return super.formatDescription()
                + String.format(" | %s", formatDateTimeForTaskList(this.dueDate));
    }
}
