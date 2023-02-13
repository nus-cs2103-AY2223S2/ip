package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    LocalDate dueDate;
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + this.dueDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ')';
    }
}
