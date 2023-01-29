package duke.tasks;

import duke.exceptions.InvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate deadline;
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("MMM dd yyyy");
    public Deadline(String command, String deadline) throws InvalidDateException {
        super(command);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(formatter) + ")";
    }

    @Override
    public String taskToData() {
        int done = isDone() ? 1 : 0;
        String task = getTask();
        return String.format("[D] | %d | %s | %s",
                done,
                task,
                this.deadline);
    }
}
