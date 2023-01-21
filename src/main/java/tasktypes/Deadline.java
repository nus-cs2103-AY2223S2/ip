package tasktypes;

public class Deadline extends Task {
    String doneBy;

    public Deadline(String description, String by) {
        super(description);
        this.doneBy = by;
        Task.numTask++;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.doneBy + ")";
    }
}


