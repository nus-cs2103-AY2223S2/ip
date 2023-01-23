import java.util.Objects;

public class Deadline extends Task {
    protected String deadline;

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        this.symbol = "D";
    }

    Deadline(String description, String isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
        this.symbol = "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.deadline + ")";
    }

    @Override
    public String getDataFormat() {
        return this.combineData(super.getDataFormat(), this.deadline);
    }
}
