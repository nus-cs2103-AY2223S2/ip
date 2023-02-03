package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate time;

    public Deadline (String description, LocalDate time) {
        super(description);
        this.time = time;
    }
    public Deadline (boolean isDone, String description, LocalDate time) {
        super(description);
        this.time = time;
        this.isDone = isDone;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String save() {
        return "D|" + (this.isDone ? 1 : 0) + "|" + this.description + "|" + this.time + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
