public class Deadlines extends Task {

    protected String deadline;
    public Deadlines(String description, String deadline) {
        this(false, description, deadline);
    }

    public Deadlines(boolean isDone, String description, String deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }
    @Override
    public String toString() {

        return "[D]" + super.toString() + String.format(" (by: %s)", this.deadline);
    }
    public String formatText() {
        String divider = " | ";
        String isMarked = this.isDone ? "1" : "0";
        return "D" + divider + isMarked + divider + this.description + divider + this.deadline;
    }

}
