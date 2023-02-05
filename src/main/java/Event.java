import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected Object from;
    protected Object to;

    public Event(String description, String from, String to) throws DukeException{
        super(description);
        this.from = from;
        this.to = to;
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
    }

    public void isDate() {
        try {
            LocalDate d1 = LocalDate.parse((String) from);
            this.from = d1;
            from = ((LocalDate) from).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception e) {
        }
        try {
            LocalDate d2 = LocalDate.parse((String) to);
            this.to = d2;
            to = ((LocalDate) to).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception e) {
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from + " to:" + to + ")";
    }
}
