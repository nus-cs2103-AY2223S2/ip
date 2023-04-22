package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//public class Deadline extends Task {
//
//    protected String by;
//
//    public Deadline(String description, String by) {
//        super(description);
//        this.by = by;
//    }
//
//    public String getBy() {
//        return by;
//    }
//
//    @Override
//    public String getTaskSymbol() {
//        return "D";
//    }
//
//    @Override
//    public String toString() {
//        return  + super.toString() + " (by: " + by + ")";
//    }
//}


public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);

        assert description != null : "Description cannot be null";
        assert by != null : "By (deadline) cannot be null";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.by = LocalDateTime.parse(by, formatter);

        assert this.by != null : "Parsed LocalDateTime cannot be null";
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String getTaskSymbol() {
        return "D";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[" + getTaskSymbol() + "]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}