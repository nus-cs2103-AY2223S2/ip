public class Deadline extends Task {
    private String by;
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    public String toString() {
        return String.format("[E]%s (%s)", super.toString(), by);
    }
}
