package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String byStr;
    protected LocalDateTime byDateTime;
    protected static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
    protected static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mma");

    /**
     * Returns a Deadline object.
     *
     * @param description Description of the task.
     * @param byStr The 'by' date.
     * @throws DukeException If description or byStr are empty.
     */
    public Deadline(String description, String byStr) throws DukeException {
        super(description.trim(), TaskIcon.DEADLINE);
        setByDate(byStr);
    }

    public Deadline(String description, String byStr, String todos) throws DukeException {
        super(description, TaskIcon.DEADLINE, todos);
        setByDate(byStr);
    }

    private void setByDate(String byStr) throws DukeException {
        this.byStr = byStr.trim();
        if (this.byStr.equals("")) {
            throw new DukeException("The 'by' date of a deadline cannot be empty.");
        }
        try {
            byDateTime = LocalDateTime.parse(byStr, inputFormatter);
        } catch (DateTimeParseException e) {
            byDateTime = null;
        }
    }

    public String getByDate() {
        return byStr;
    }

    @Override
    public String toString() {
        return String.format(
            "%s (by: %s)",
            super.toString(),
            byDateTime == null ? byStr : byDateTime.format(outputFormatter)
        );
    }
}
