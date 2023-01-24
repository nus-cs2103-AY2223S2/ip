public class Deadline extends Task {
    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        if (super.getStatus()) {
            return String.format("[D][X] %s (by: %s)\n", super.getTaskName(), this.deadline);
        } else {
            return String.format("[D][ ] %s (by: %s)\n",super.getTaskName(), this.deadline);
        }
    }
}
