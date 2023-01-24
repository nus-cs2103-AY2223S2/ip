public class Deadline extends Task {
    String deadline;
    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }
    public Deadline(String title, String deadline, boolean isDone) {
        super(title, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toDiskFormat() {
        return String.format("D|%d|%s|%s", super.getIsDone() ? 1 : 0, super.getTitle(), this.deadline);
    }

    @Override
    public String toString() {
        return String.format("[%s][D] %s (by: %s)", super.getIsDone() ? "X" : " ",
                super.getTitle(), this.deadline);
    }
}
