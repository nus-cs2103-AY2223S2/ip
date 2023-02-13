package sam.task;

import java.time.LocalDate;
import java.util.Map;

import sam.parser.Parser;
import sam.parser.SamInvalidDateException;

/**
 * Represents a task with a due date.
 */
public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String title, LocalDate by) {
        this(title, by, false);
    }

    /**
     * Constructs a new Deadline task.
     *
     * @param title The title of the task.
     * @param by The due date for the Deadline.
     * @param isDone Indicates whether the task is done.
     */
    public Deadline(String title, LocalDate by, boolean isDone) {
        super(title, isDone);
        this.by = by;
    }

    @Override
    public String getDescription() {
        return String.format("by: %s", formatDateDisplay(by));
    }

    @Override
    public void update(Map<String, String> argsMap) throws SamInvalidDateException {
        super.update(argsMap);

        if (argsMap.containsKey("by")) {
            this.by = Parser.parseDate(argsMap.get("by"));
        }
    }

    @Override
    public String toSaveFormat() {
        return String.format(
                "D | %d | %s | %s",
                getStatusNo(), getTitle(), formatDateSave(by));
    }

    @Override
    public String toString() {
        return String.format(
                "[D][%c] %s (by: %s)",
                getStatusIcon(), getTitle(), formatDateDisplay(by));
    }
}
