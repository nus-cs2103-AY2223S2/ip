package jane.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadline;
    public Deadline(int num, String description, LocalDateTime deadline) {
        super(num, description);
        this.deadline = deadline;
    }
    @Override
    public String save() {
        int i = 0;
        if (this.isDone) {
            i = 1;
        }
        return String.format("D|%d| %s |%s", i, this.description, this.deadline);
    }
    @Override
    public String toString() {
        String parsed = deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("%d. [D][%s] %s(%s)", this.num, this.getStatusIcon(), this.description, parsed);
    }

}

