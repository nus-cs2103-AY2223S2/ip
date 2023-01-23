package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDateException;

public class Deadline extends Task {

    private LocalDate deadline;

    public Deadline(String name, String deadline) throws InvalidDateException {
        super(name);
        try {
            this.deadline = Task.parseDate(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(e.getParsedString());
        }
    }

    @Override
    protected String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        String s = String.format("%s (by: %s)", super.toString(), Task.formatDate(this.deadline));
        return s;
    }
}
