package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    protected LocalDateTime byObj;
    protected static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    protected static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mma");
    public Deadline(String description, String by) throws DukeException {
        super(description.trim(), TaskSymbol.DEADLINE);
        this.by = by.trim();
        if (this.description.equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        if (this.by.equals("")) {
            throw new DukeException("The 'by' date of a deadline cannot be empty.");
        }
        try {
            byObj = LocalDateTime.parse(by, inputFormatter);
        } catch (DateTimeParseException e) {
            byObj = null;
        }
    }

    /**
     * Getter function for by date
     * @return The by date
     */
    public String getByDate() {
        return by;
    }
    /**
     * Represent duke.Deadline as a string
     * @return String representation of a duke.Deadline
     */
    @Override
    public String toString() {
        return String.format(
            "%s (by: %s)", 
            super.toString(), 
            byObj == null ? by : byObj.format(outputFormatter)
        );
    }
}
