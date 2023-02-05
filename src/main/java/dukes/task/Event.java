package dukes.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Subclass of Task that handles tasks with "event" label.
 */
public class Event extends Task {
    LocalDate start;
    LocalDate end;

    /**
     * Constructor of Event class.
     *
     * @param taskName name (main content) of the task.
     * @param start start date of the event.
     * @param end end date of the event.
     */
    public Event(String taskName, LocalDate start, LocalDate end) {
        super(taskName);
        this.tag = "E";
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor of Event class.
     *
     * @param taskName name (main content) of the task.
     * @param isDone specifies if the task has been done or not.
     * @param start start date of the event.
     * @param end end date of the event.
     */
    public Event(String taskName, boolean isDone, LocalDate start, LocalDate end) {
        super(taskName, isDone);
        this.tag = "E";
        this.start = start;
        this.end = end;
    }

    @Override
    public LocalDate getStart() {
        return this.start;
    }

    @Override
    public LocalDate getEnd() {
        return this.end;
    }

    @Override
    public void setStart(LocalDate newStart) {
        this.start = newStart;
    }

    @Override
    public void setEnd(LocalDate newEnd) {
        this.end = newEnd;
    }

    /**
     * Returns a string containing {tag "E" for event task} +
     * {if the task is completed} + the content of the task.
     *
     * @return a string showing its a event task,
     * if the task is completed, its content, start and end date.
     */
    @Override
    public String toString() {
        String startFormat = this.start.format(
                DateTimeFormatter.ofPattern("MMM d yyyy", new Locale("en"))
        );
        String endFormat = this.end.format(
                DateTimeFormatter.ofPattern("MMM d yyyy", new Locale("en"))
        );
        return "[E]" + super.toString() + " (from: " +
                startFormat + " to: " + endFormat + ")";
    }
}
