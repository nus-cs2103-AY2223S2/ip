public class TODO extends Task {
    private String task;

    public TODO(String task) {
        super(task);
        this.task = task;

    }

    @Override
    public boolean isValid() {
        if (this.task.length() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
