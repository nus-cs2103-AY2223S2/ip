import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    Deadline(String title, String by) throws DukeException {
        super(title);
        try {
            this.by = LocalDateTime.parse(by.replace("/by", "").trim());
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException(Views.DATE_PARSE_ERR_STRING.eng());
        }
    }

    Deadline(String title, String by, boolean done) throws DukeException {
        super(title, done);
        try {
            this.by = LocalDateTime.parse(by.replace("/by", "").trim());
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException(Views.DATE_PARSE_ERR_STRING.eng());
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
