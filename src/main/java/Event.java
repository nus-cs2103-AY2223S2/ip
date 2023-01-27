import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected String from;
    protected String to;
    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        if (from.matches("\\d{4}-\\d{2}-\\d{2}")){
            LocalDate date = LocalDate.parse(from);
            this.from = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        this.to = to;
        if (to.matches("\\d{4}-\\d{2}-\\d{2}")){
            LocalDate date = LocalDate.parse(to);
            this.to = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.from + " to:" + this.to + ")";
    }
}
