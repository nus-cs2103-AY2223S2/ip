import exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Parse Error: " + e.getMessage() +  "\n" +
                    "\tAccepted format: \"YYYY-MM-DD\"");
        }
    }

    @Override
    public String getData() {
        StringBuilder sb = new StringBuilder();
        sb.append("D | ");
        if (this.isDone) {
            sb.append("1 | ");
        } else {
            sb.append("0 | ");
        }
        sb.append(this.description).append(" | ");
        sb.append(this.by).append("\n");;
        return sb.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.getMonth().toString().substring(0, 3) + " " +
                by.getDayOfMonth() + " " +
                by.getYear() + ")";
    }
}
