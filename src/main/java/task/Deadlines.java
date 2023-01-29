package task;
import java.time.LocalDateTime;

public class Deadlines extends Task {
    private LocalDateTime deadLine;

    public Deadlines(String taskName, LocalDateTime deadLine) {
        super(taskName);
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + deadLine + ")";
    }
}
