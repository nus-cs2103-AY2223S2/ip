public class Deadline extends Task {
    String deadline;
    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[%s][D] %s (by: %s)", super.getIsDone() ? "X" : " ",
                super.getTitle(), this.deadline);
    }
}
