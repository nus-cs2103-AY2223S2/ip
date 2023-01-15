public class TODO extends Task {
    private String taskName;
    public TODO(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
