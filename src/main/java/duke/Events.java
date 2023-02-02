package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected LocalDate startDate;
    protected LocalDate endDate;
    public Events(String name, String startDate, String endDate) {
        super(name, "E");
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    public Events(String name, String startDate, String endDate, boolean isDone) {
        super(name, "E", isDone);
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Override
    public String description() {
        return String.format("%s (from: %s to: %s)", super.description(),
                this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String formatDescription() {
        return super.formatDescription()
                + String.format(" | %s | %s", startDate.toString(),
                endDate.toString());
    }
}
