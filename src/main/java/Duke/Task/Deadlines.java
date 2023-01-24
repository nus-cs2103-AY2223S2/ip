package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task{
    private final LocalDateTime deadline;
    private final String stringDeadline;

    public Deadlines(String description, boolean isDone, LocalDateTime deadline) {
        super(description, isDone);
        this.deadline = deadline;
        this.stringDeadline = deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"));
    }

    public Deadlines(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = null;
        this.stringDeadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                stringDeadline);
    }

    @Override
    public String save() {
        return String.format("deadline %s-%s-%s\n", this.description, this.isDone, this.deadline);
    }
}
