package dukes.engine;

class DeadLine extends Task {
    String deadline;

    DeadLine(String taskName, String deadline) {
        super(taskName);
        this.tag = "deadline";
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.deadline + ")";
    }
}
