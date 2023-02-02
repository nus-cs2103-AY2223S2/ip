package Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Task.Task;

public class Deadline extends Task {
    
    protected DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected String by;
    protected LocalDateTime byDate;
    protected String displayBy;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        byDate = LocalDateTime.parse(by, FORMAT);
        displayBy = this.byDate.toString().replace("T", " ");
    }

    public Deadline(boolean isDone, String description, String by) {
        super(description);
        this.by = by;
        this.isDone = isDone;
        byDate = LocalDateTime.parse(by, FORMAT);
        displayBy = this.byDate.toString().replace("T", " ");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + displayBy + ")";
    }

    @Override
    public String getCommand() {
        return this.isDone
        ? "1 deadline " + this.description + " /by " + this.by
        : "0 deadline " + this.description + " /by " + this.by;
    }
}
