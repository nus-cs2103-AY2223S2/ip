package duke.task;
import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private LocalDate by;
    public Deadline(String taskName, String by) throws DukeException {
        super(taskName);
        this.type = "D";
        try {
            LocalDate byDate = LocalDate.parse(by);
            this.by = byDate;
        } catch (DateTimeParseException e) {
            throw new DukeException("Format of date was not recognized");
        }
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }

    @Override
    public String encode() {
        return String.format("%s | %s | %s | %s", this.type, this.isDone, this.taskName, this.by);
    }
}
