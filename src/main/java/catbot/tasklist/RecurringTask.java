package catbot.tasklist;

import java.time.Duration;
import java.time.LocalDateTime;

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

    // from https://stackoverflow.com/questions/3471397/how-can-i-pretty-print-a-duration-in-java
    private static String formatDuration(Duration duration) {
        long days = duration.toDays();
        return ((days != 0 ? days + " Days " : 0)
                + duration.minusDays(days).toString()
                .substring(2))
                .replaceAll("(\\d[HMS])(?!$)", "$1 ")
                .replace("H", " Hours")
                .replace("M", " Minutes")
                .replace("S", " Seconds")
                .replaceAll("\\b1 Days", "1 Day")
                .replaceAll("\\b1 Hours", "1 Hour")
                .replaceAll("\\b1 Minutes", "1 Minute")
                .replaceAll("\\b1 Seconds", "1 Second")
                .replaceAll("\\b0 Seconds", "");
    }

    @Override
    public String toString() {
        while (Duration.between(LocalDateTime.now(), nextOccurrence).isNegative()) {
            nextOccurrence = nextOccurrence.plus(repeatsEvery);
            this.setDone(false);
        }

        String formattedDate = formatDate(nextOccurrence);
        return "[R]" + super.toString() + (formattedDate.charAt(2) == ':' ? " at " : " on ") + formattedDate
                + " every " + formatDuration(repeatsEvery);
    }

    @Override
    public String toCommand() {
        return "recurring " + super.description + " /on " + nextOccurrence + " /every " + formatDuration(repeatsEvery)
                + (super.isDone ? "\nmark 0" : "");
    }
}
