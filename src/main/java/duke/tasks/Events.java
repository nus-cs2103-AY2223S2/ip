package duke.tasks;

import java.time.LocalDateTime;

import duke.DukeException;
/**
 * Events task tracks when the task starts and ends.
 *
 * @author Cheam Jia Wei
 */
public class Events extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor for Events task.
     *
     * @param name Name of the task.
     * @param start Date of when the task starts.
     * @param end Date of when the task ends.
     */
    public Events(String name, String start, String end) {
        super(name);
        this.start = LocalDateTime.parse(start);
        this.end = LocalDateTime.parse(end);
    }

    @Override
    public boolean isWithinDate(LocalDateTime date) {
        return date.isBefore(end) && date.isAfter(start);
    }

    /**
     * Updates the Event task with the provided details.
     *
     * @param input The details to update the task with.
     * @throws DukeException If the input does not provide sufficient information.
     */
    @Override
    public void update(String input) throws DukeException {
        String[] split = input.split(" /from | /to ");
        if (split.length < 3) {
            throw new DukeException("Insufficient information to update");
        }
        super.update(split[0]);
        this.start = LocalDateTime.parse(split[1]);
        this.end = LocalDateTime.parse(split[2]);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + dateFormat(this.start)
                + " to: " + dateFormat(this.end) + ")";
    }

    @Override
    public String toWrite() {
        return "E | " + super.toWrite() + " | " + this.start
                + " | " + this.end + "\n";
    }
}
