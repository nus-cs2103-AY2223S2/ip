import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Contains information of a deadline
 * Contains description and deadline of the deadline
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a deadline object
     *
     * @param description The description of the deadline
     * @param by Deadline time of the deadline
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Deadline could not be parsed to datetime");
        }
    }

    /**
     * Creates a deadline object
     *
     * @param description The description of the deadline
     * @param by Deadline time of the deadline
     */
    public Deadline(String description, String by, boolean isDone) throws DukeException {
        super(description, isDone);
        this.by = LocalDate.parse(by);
    }

    public static Deadline generate(String input) throws DukeException {
        // Cleans input and checks for description and deadline
        try {
            input = input.trim()
                    .substring(9)
                    .trim();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Deadline missing description and deadline");
        }

        try {
            int index = input.indexOf("/by");
            if (index < 0) {
                throw new DukeException("Deadline missing deadline");
            } else if (index == 0) {
                throw new DukeException("Deadline missing description");
            }

            // Generates Deadline task
            String description = input.substring(0, index - 1);
            String by = input.substring(index + 4);
            return new Deadline(description, by);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Deadline missing deadline");
        }

    }

    public static Deadline load(String input, boolean isDone) throws DukeException {
        try {
            // Cleans input and checks if fields are empty
            input = input.trim();
            if (input.equals("")) {
                throw new DukeException("Deadline missing description and deadline");
            }

            // Checks for separator
            int index = input.lastIndexOf("|");
            if (index < 0) {
                throw new DukeException("Deadline missing deadline");
            } else if (index == 0) {
                throw new DukeException("Deadline missing description");
            }

            // Generates Deadline task
            String description = input.substring(0, index - 1);
            String by = input.substring(index + 2);
            return new Deadline(description, by, isDone);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Deadline missing deadline");
        }
    }

    /**
     * Returns type of task, completion status, description, and deadline of
     * the deadline
     *
     * @return Type of task, completion status, description, and deadline of
     * the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter
                        .ofPattern("MMM d yyyy"))
                + ")";
    }

    @Override
    public String save() {
        return "D | " + getStatusIcon()
                + " | " + description
                + " | " + by;
    }
}
