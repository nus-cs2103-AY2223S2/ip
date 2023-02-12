package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Locale;

/**
 * Represents task of the type 'deadline'.
 */
public class Deadline extends Task {
    private LocalDateTime time = null;

    /**
     * Constructs a new Deadline instance with the given content, date and isDone state.
     * It also stores the string data as a LocalDateTime object.
     * @param content content of the task.
     * @param date deadline of the task.
     * @param isDone whether the task is done.
     */
    public Deadline(String content, String date, boolean isDone) {
        super(content, isDone);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        format.withLocale(Locale.ENGLISH);
        this.time = LocalDateTime.parse(date, format);
        this.type = 'D';
    }

    /**
     * Returns the string representation of the Deadline object.
     * @return String
     */
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm");
        String time = this.time.format(format);
        return "[" + this.getTypeIcon() + "]"
                + "[" + this.getStatusIcon() + "] " + this.getTaskContent() + " (by: " + time + ")";
    }
}
