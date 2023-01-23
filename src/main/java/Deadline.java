import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getStorageDetails() {
        return this.description + " | " + this.date.toString();
    }

    @Override
    public String eventType() {
        return "D";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return "[D]" + super.toString() + " (by: " + this.date.format(formatter) + ")";
    }
}