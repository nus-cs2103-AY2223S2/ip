// deadline is a type of task that takes in an additional string
// as its deadline
public class Deadline extends Task {
    private String deadlineTiming;

    public Deadline(String description, String deadlineTiming) {
        super(description);
        this.deadlineTiming = deadlineTiming;
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString() + "(by: " + deadlineTiming + ")";
    }
}