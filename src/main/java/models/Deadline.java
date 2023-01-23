package models;

public class Deadline extends Item {
    private final String deadline;

    public Deadline(String content, String deadline) {
        super(content);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
