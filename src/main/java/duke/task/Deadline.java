package duke.task;

import duke.TaskCreationException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    LocalDate deadline;

    public Deadline(String desc, String deadline) throws TaskCreationException {
        super(desc);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new TaskCreationException("Error parsing date");
        }
    }

    @Override
    protected String getType() {
        return "[D]";
    }

    @Override
    public String toString() {
        return String.format("%s%s %s (by: %s)", getType(), getStatusIcon(), this.desc, deadline);
    }
}
