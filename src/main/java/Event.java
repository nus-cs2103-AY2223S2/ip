import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    Event(String title, String from, String to) throws DukeException {
        super(title);
        try {
            this.from = LocalDateTime.parse(from.replace("/from", "").trim());
            this.to = LocalDateTime.parse(to.replace("/to", "").trim());
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException(Views.DATE_PARSE_ERR_STRING.eng());
        }
        if (this.to.isBefore(this.from)) {
            throw new DukeException(Views.DATE_WRONG_ORDER_STRING.eng());
        }
    }

    Event(String title, String from, String to, boolean done) throws DukeException {
        super(title, done);
        try {
            this.from = LocalDateTime.parse(from.replace("/from", "").trim());
            this.to = LocalDateTime.parse(to.replace("/to", "").trim());
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException(Views.DATE_PARSE_ERR_STRING.eng());
        }
        if (this.to.isBefore(this.from)) {
            throw new DukeException(Views.DATE_WRONG_ORDER_STRING.eng());
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}