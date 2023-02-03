package task;

import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate ddl;
    protected String name;

    public Deadline(String name) {
        super(name);
        this.name = name.substring("deadline".length() + 1, name.indexOf("/by") - 1);
        this.ddl = LocalDate.parse(name.substring(name.indexOf("/by") + 4));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString().substring(0, "[ ] ".length()) +
                this.name + " (by: " + ddl.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }


}
