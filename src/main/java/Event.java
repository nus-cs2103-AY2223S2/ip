import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    LocalDate from;
    LocalDate to;

    @Override
    protected String getType() {
        return "[E]";
    }

    public Event(String desc, String from, String to) throws TaskCreationException {
        super(desc);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e){
            System.out.println(e.getMessage());
            throw new TaskCreationException("Error parsing date");
        }
    }

    @Override
    public String toString() {
        return String.format("%s%s %s (From%sto%s)", getType(), getStatusIcon(), this.desc, this.from, this.to);

    }
}
