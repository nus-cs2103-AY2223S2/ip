import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    LocalDate from;
    LocalDate to;

    Event(String name, String from, String to) throws DateTimeException {
        super(name);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new DateTimeException();
        }
    }

    @Override
    public String storageFormat() {
        return String.join("|", "E", super.storageFormat(), from.toString(), to.toString()) + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }
}
