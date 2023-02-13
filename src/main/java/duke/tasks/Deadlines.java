package duke.tasks;

import java.time.LocalDateTime;

import duke.DukeException;

/**
 * Deadlines tasks tracks when the task has to be completed by.
 *
 * @author Cheam Jia wei
 */
public class Deadlines extends Task {
    private LocalDateTime end;

    /**
     * Constructor for Deadlines task.
     *
     * @param name Name of task.
     * @param end Date task has to be completed by
     */
    public Deadlines(String name, String end) {
        super(name);
        this.end = LocalDateTime.parse(end);
    }

    @Override
    public boolean isWithinDate(LocalDateTime date) {
        return date.isBefore(end);
    }

    /**
     * Updates the Deadline task with the provided details.
     *
     * @param input The details to update the task with.
     * @throws DukeException If the input does not provide sufficient information.
     */
    @Override
    public void update(String input) throws DukeException {
        String[] split = input.split(" /by ");
        if (split.length < 2) {
            throw new DukeException("Insufficient information to update");
        }
        super.update(split[0]);
        this.end = LocalDateTime.parse(split[1]);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + dateFormat(this.end) + ")";
    }

    @Override
    public String toWrite() {
        return "D | " + super.toWrite() + " | " + this.end + "\n";
    }
}
