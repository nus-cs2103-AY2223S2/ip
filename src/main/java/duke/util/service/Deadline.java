class Deadline extends Task {
    String deadline;
    public Deadline(String deadline, String action) {
        super("D", action);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString() + " (BY: " + this.deadline + ")";
    }
}