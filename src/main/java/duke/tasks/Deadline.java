package duke.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


import duke.DukeException;

/**
 * Deadline is a subclass of Task that has a due_by date
 */
public class Deadline extends Task{
    private LocalDate due_by;
    private String output;

    // A constructor that takes in a description and a due_by date. It then parses the due_by date and
    // formats it to a new format. If the date is not in the correct format, it will throw a
    // DukeException.
    public Deadline(String description, String due_by) throws DukeException{
        super(description);
        try {
            this.due_by = LocalDate.parse(due_by);
            DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
            this.output = this.due_by.format(newFormat);
        } catch (DateTimeParseException e) {
            throw new DukeException(("Invalid deadline format, please input as YYYY-MM-DD with a space after /due_by"));
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (due_by: " + this.output  + ")";
    }
}
