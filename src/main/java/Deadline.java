public class Deadline extends Task {

    private String deadline;

    public Deadline(String command, String deadline) {
        super(command);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }

    @Override
    public String taskToData() {
        int done = isDone() ? 1 : 0;
        String task = getTask();
        return String.format("[D] | %d | %s | %s",
                done,
                task,
                this.deadline);
    }
}
