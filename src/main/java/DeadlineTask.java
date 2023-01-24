import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    private final LocalDate by;

    DeadlineTask(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    public boolean isDueOn(LocalDate date) {
        return this.by.equals(date);
    }

    public String toDukeFileString() {
        return "D|" + super.toDukeFileString() + "|" + this.by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String formattedByString = this.by.format(formatter);
        return "[D]" + super.toString() + " (by: " + formattedByString + ")";
    }
}
