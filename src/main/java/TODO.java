public class TODO extends Task {
    private String task;

    public TODO(String task) {
        super(task);
        this.task = task;

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
