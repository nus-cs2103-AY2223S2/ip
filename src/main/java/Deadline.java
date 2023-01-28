import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task{
    private final LocalDateTime dueAt;

    public Deadline(String task, LocalDateTime dueAt) {
        super(task);
        this.dueAt = dueAt;
    }

    private String formatDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by " + formatDate(dueAt);
    }
}
