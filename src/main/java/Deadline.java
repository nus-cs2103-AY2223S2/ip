public class Deadline extends Task {
    String doneBy;

    Deadline(String description, String doneBy) {
        super(description);
        this.doneBy = doneBy;
    }

    Deadline(String description, boolean isDone, String doneBy) {
        super(description, isDone);
        this.doneBy = doneBy;
    }

    @Override
    public String toString() {
        return String.format("D | %s | by: %s", super.toString(), doneBy);
    }
}
