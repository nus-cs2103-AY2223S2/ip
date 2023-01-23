package tasks;

import exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, String start, String end) throws DukeException {
        super(description);
        try {
            this.start =  LocalDate.parse(start);
            this.end =  LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            throw new DukeException("Parse Error: " + e.getMessage() +  "\n" +
                    "\tAccepted format: \"YYYY-MM-DD\"");
        }

    }

    @Override
    public String getData() {
        StringBuilder sb = new StringBuilder();
        sb.append("E | ");
        if (this.isDone) {
            sb.append("1 | ");
        } else {
            sb.append("0 | ");
        }
        sb.append(this.description).append(" | ");
        sb.append(this.start).append(" | ").append(this.end).append("\n");;
        return sb.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
        " (from: " + start.getMonth().toString().substring(0, 3) + " " + start.getDayOfMonth() + " " + start.getYear() +
        " to: " + end.getMonth().toString().substring(0, 3) + " " + end.getDayOfMonth() + " " + end.getYear() + ")";
    }
}
