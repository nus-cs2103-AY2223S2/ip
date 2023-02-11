public class Deadline extends Task {

    protected String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String taskToData() {
        return String.format("[D] | %s | %s | %s", this.getStatusIcon(), this.name, this.deadline);
    }

    @Override
    public String toString() {
        String[] strArr = deadline.split(" ", 2);
        return String.format("%s (%s: %s)", name, strArr[0], strArr[1]);
    }
}
