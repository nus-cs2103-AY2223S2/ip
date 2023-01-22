import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private LocalDate startDate;
    private LocalDate endDate;

    public Event(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.startDate.format(DateTimeFormatter.ofPattern("d MMM uuuu"))
                + " to: " + this.endDate.format(DateTimeFormatter.ofPattern("d MMM uuuu")) + ")";
    }
}
