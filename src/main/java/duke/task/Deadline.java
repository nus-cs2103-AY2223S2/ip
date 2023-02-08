package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime dl;

    public Deadline(String name, String deadline) throws DateTimeParseException {
        super(name);
        this.dl = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm"));
    }

    public Deadline(String name, String deadline, String status, String tag) {
        super(name, status, tag);
        try {
            this.dl = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm"));
        } catch (DateTimeParseException e) {
            this.dl = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %d %d %02d:%02d, %s)",
                super.toString(), dl.getMonth(), dl.getDayOfMonth(),
                dl.getYear(), dl.getHour(), dl.getMinute(), dl.getDayOfWeek());
    }

    @Override
    public String asTokens() {
        return "D," + super.asTokens() + ',' + this.dl;
    }
}
