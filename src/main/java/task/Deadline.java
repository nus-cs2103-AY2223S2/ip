package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    LocalDateTime dueDate;

    Deadline(String content, LocalDateTime dueDate) {
        super(content);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        String printDateTime = this.dueDate.format(DateTimeFormatter.ofPattern("HHmm MMM d yyyy"));
        return String.format("[D]%s (by: %s)", super.toString(), printDateTime);
    }
}
