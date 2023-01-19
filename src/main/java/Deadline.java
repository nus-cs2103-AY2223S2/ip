public class Deadline extends Task {

    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    protected String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        String s = String.format(
            "%s (by: %s)",
            super.toString(),
            this.deadline
        );
        return s;
    }
}
