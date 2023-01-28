import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task{
    private final String taskType = "[D]";
    private LocalDate byTime;

    public Deadline(String description, String by) {
        super(description);
        this.byTime = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return taskType + super.toString() + " (by: "
                + DateTimeFormatter.ofLocalizedDate((FormatStyle.FULL)).format(this.byTime) + ")";
    }
}
