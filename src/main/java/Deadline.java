import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    LocalDate deadline;

    Deadline(String name, String by) throws DateTimeException {
        super(name);
        try {
            this.deadline = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DateTimeException();
        }
    }

    @Override
    public String storageFormat() {
        return String.join("|", "D", super.storageFormat(), deadline.toString())  + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }
}
