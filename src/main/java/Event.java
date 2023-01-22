import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    Event(String name, String from, String to) {
        super(name);
        this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.to = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + from.getMonth() +
                ' ' + from.getDayOfMonth() + ' ' +
                from.getYear() + " to: " +
                to.getMonth() + ' ' +
                to.getDayOfMonth() +
                ' ' + to.getYear() + ")";
    }
}
