import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline, which is a type of Task that has to be done before s specific date/time.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String description) throws DukeException {
        super(description.split(" /by ")[0]);
        try {
            this.deadline = this.parseDatetime(description.split(" /by ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ I'm sorry, but Fake Duke doesn't know what that means :-(");
        } catch (DateTimeParseException dtpe) {
            throw new DukeException("Invalid datetime format. Please use yyyy-mm-dd HH:mm (E.g. 2019-10-15 18:00).");
        }
    }

    /**
     * Returns the String representation of a Deadline.
     *
     * @return  String representation of a Deadline in this format: [type][<status>] <description> (by: <deadline>).
     */
    @Override
    public String toString() {
        return String.format("[D][%c] %s (by: %s)", this.getStatusIcon(), this.description, this.getStringDatetime(this.deadline));
    }

    @Override
    public String getRawTask() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("D ~ %d ~ %s ~ %s\n", isDone ? 1 : 0, this.description, dtf.format(this.deadline));
    }
}
