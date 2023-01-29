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
        DukeException.ErrorType errType = DukeException.ErrorType.TIME;
        try {
            this.by = LocalDate.parse(by);
            if (LocalDate.now().isAfter(this.by)) {
                throw new DukeException(errType, "Deadline reached");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException(errType, "DateTime Parse Exception");
        }
    }

    /**
     * Creates a deadline object from user input
     * Handles exceptions
     *
     * @param input Input from user
     * @return Deadline Task object
     * @throws DukeException If description of the deadline is empty
     * @throws DukeException If deadline of the deadline is empty
     */
    public static Deadline generate(String input) throws DukeException {
        // Cleans input command
        input = input.trim();
        DukeException.ErrorType errType = DukeException.ErrorType.DEADLINE;

        // Checks format of input command
        int indexDesc = input.indexOf(" ");
        int indexTime = input.indexOf(" /by ");
        if (indexDesc < 0) {
            throw new DukeException(errType, "Empty description");
        } else if (indexTime < 0) {
            throw new DukeException(errType, "Empty deadline");
        }

        // Cleans and checks variables
        String description = input.substring(indexDesc + 1, indexTime).trim();
        String deadline = input.substring(indexTime + 5).trim();
        if (description.equals("")) {
            throw new DukeException(errType, "Empty description");
        } else if (deadline.equals("")) {
            throw new DukeException(errType, "Empty deadline");
        }

        return new Deadline(description, deadline);
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
}
