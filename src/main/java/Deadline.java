import java.time.LocalDateTime;
public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String value, LocalDateTime by) {
        super(value);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }


}
