package catbot.tasklist;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Handles tasks which occur repeatedly at regular intervals
 */
public class RecurringTask extends Task {
    private LocalDateTime nextOccurrence;
    private final Duration repeatsEvery;

    /**
     * Constructs a new RecurringTask which handles repeating tasks
     *
     * @param description    is the description of the task
     * @param nextOccurrence is when the task will happen next
     * @param repeatsEvery   is the time between each occurrence
     */
    public RecurringTask(String description, LocalDateTime nextOccurrence, Duration repeatsEvery) {
        super(description);
        this.nextOccurrence = nextOccurrence;
        this.repeatsEvery = repeatsEvery;
    }

    private String formatDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
    }

    // from https://stackoverflow.com/questions/3471397/how-can-i-pretty-print-a-duration-in-java
    private static String formatDuration(Duration duration) {
        return duration.toString()
                .substring(2)
                .replaceAll("(\\d[HMS])(?!$)", "$1 ")
                .toLowerCase();
    }

    @Override
    public String toString() {
        while (Duration.between(LocalDateTime.now(), nextOccurrence).isNegative()) {
            nextOccurrence = nextOccurrence.plus(repeatsEvery);
        }
        return "[R]" + super.toString() + " at " + formatDate(nextOccurrence)
                + " every " + formatDuration(repeatsEvery);
    }

    @Override
    public String toCommand() {
        return "recurring " + super.description + " /at " + nextOccurrence + " /every " + repeatsEvery
                + (super.isDone ? "\nmark 0" : "");
    }
}
