import java.util.Objects;

public class Deadline extends Task {

    protected String deadline;

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        this.symbol = "D";
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.deadline + ")";
    }
}
