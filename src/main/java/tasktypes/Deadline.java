package tasktypes;

public class Deadline extends Task {
    public String doneBy;

    public Deadline(String description, String by) {
        super(description);
        this.doneBy = by;
        Task.numTask++;
    }

    @Override
    public String getSaveFormat() {
        String done;
        if (this.done) {
            done = "1";
        } else {
            done = "0";
        }
        return "D" + ",," + done + ",," + this.description + ",," + doneBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.doneBy + ")";
    }
}


