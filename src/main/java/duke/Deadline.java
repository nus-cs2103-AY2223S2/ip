package duke;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    LocalDate date;
    public Deadline(String description, String duration) throws NeroException {
        super(description);
        try {
            this.date = LocalDate.parse(duration.trim());
        } catch (DateTimeParseException e) {
            throw new NeroException("Invalid Date!");
        }
    }

    public Deadline(String description, boolean isDone, String duration) throws NeroException {
        super(description, isDone);
        try {
            this.date = LocalDate.parse(duration);
        } catch (DateTimeParseException e) {
            throw new NeroException("Invalid Date!");
        }
    }

    public LocalDate getDate() {
        return date;
    }
    public String getTaskIcon() {
        return "D";
    }

    public String dateFormatter() {
        return "by: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));

    }

    public String toSave() {
        return this.getTaskIcon() + SEPARATOR + convertBoolean()
                + SEPARATOR + this.getDescription()
                + SEPARATOR + this.date;
    }
    @Override
    public String toString() {
        return String.format("[%s]%s %s %s", this.getTaskIcon(), this.getStatusIcon(),
                this.getDescription(), this.dateFormatter());

    }
}
