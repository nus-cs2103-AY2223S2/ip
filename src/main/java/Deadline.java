public class Deadline extends Task {
    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toSave() {
        if (super.isDone()) {
            return "D | 1 | " + super.getName() + " | " + this.deadline + "\n";
        } else {
            return "D | 0 | " + super.getName() + " | " + this.deadline + "\n";
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
