package catbot.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Stores an event task which happens from a certain time to a certain time.
 */
public class EventTask extends Task {
    private final LocalDateTime startsAt;
    private final LocalDateTime endsAt;

    /**
     * Initialises a new EventTask.
     * @param task is the description of the task.
     * @param from is when the task starts.
     * @param to is when the task ends.
     */
    public EventTask(String task, LocalDateTime from, LocalDateTime to) {
        super(task);
        startsAt = from;
        endsAt = to;
    }

    private String formatDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (" + formatDate(startsAt)
                + " – " + formatDate(endsAt) + ")";
    }

    @Override
    public String toCommand() {
        return "event " + super.description
                + " /from " + startsAt
                + " /to " + endsAt
                + (super.isDone ? "\nmark 0" : "");
    }
}
