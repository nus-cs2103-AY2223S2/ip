import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        super.type = 'D';
    }

    @Override
    public String taskInFileFormat() {
        return super.taskInFileFormat() + " | " + by;
    }

    @Override
    public String toString() {
        return "[" + super.type + "]" + super.toString() + " (by: " + Duke.getDateTimeOutput(by) + ")";
    }
}
