package Duke.Task;
import Duke.DateTime.DateTime;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        String deadline = DateTime.getDateTimeString(this.deadline);
        if (super.getStatus()) {
            return String.format("[D][X] %s (by: %s)\n", super.getTaskName(), deadline);
        } else {
            return String.format("[D][ ] %s (by: %s)\n",super.getTaskName(), deadline);
        }
    }
}
