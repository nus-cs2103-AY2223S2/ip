import java.time.LocalDate;

public class Event extends Task{
    LocalDate from;
    LocalDate to;

    Event(String name, String from, String to) {
        super(name);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String storageFormat() {
        return String.join("|", "E", super.storageFormat(), from, to) + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }
}
