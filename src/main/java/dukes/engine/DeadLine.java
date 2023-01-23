package dukes.engine;

class DeadLine extends Task {
    String deadline;

    DeadLine(String taskName, String deadline) {
        super(taskName);
        this.tag = "D";
        this.deadline = deadline;
    }

    DeadLine(String taskName, boolean isDone, String deadline) {
        super(taskName, isDone);
        this.tag = "D";
        this.deadline = deadline;
    }

    @Override
    String getDeadLine() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.deadline + ")";
    }
}
