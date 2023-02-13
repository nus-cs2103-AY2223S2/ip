package sam.task;

import java.time.LocalDate;
import java.util.Map;

import sam.parser.Parser;
import sam.parser.SamInvalidDateException;

/**
 * Represents a task with a start and end date.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String title, LocalDate from, LocalDate to) {
        this(title, from, to, false);
    }

    /**
     * Constructs a new Event task.
     *
     * @param title The title of the task.
     * @param from The start date for the Event.
     * @param to The end date for the Event.
     * @param isDone Indicates whether the task is done.
     */
    public Event(String title, LocalDate from, LocalDate to, boolean isDone) {
        super(title, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDescription() {
        return String.format("from: %s to: %s", formatDateDisplay(from), formatDateDisplay(to));
    }

    @Override
    public void update(Map<String, String> argsMap) throws SamInvalidDateException {
        super.update(argsMap);

        if (argsMap.containsKey("from")) {
            this.from = Parser.parseDate(argsMap.get("from"));
        }

        if (argsMap.containsKey("to")) {
            this.to = Parser.parseDate(argsMap.get("to"));
        }
    }

    @Override
    public String toSaveFormat() {
        return String.format(
                "E | %d | %s | %s | %s",
                getStatusNo(), getTitle(), formatDateSave(from), formatDateSave(to));
    }

    @Override
    public String toString() {
        return String.format(
                "[E][%c] %s (from: %s to: %s)",
                getStatusIcon(), getTitle(), formatDateDisplay(from), formatDateDisplay(to));
    }
}
