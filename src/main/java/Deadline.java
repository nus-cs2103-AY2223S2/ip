public class Deadline extends Task {

    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public void mark() {
        super.mark();
        System.out.println(String.format(" [%s][%s] %s (by: %s)",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.deadline));
    }

    @Override
    public void unmark() {
        super.unmark();
        System.out.println(String.format(" [%s][%s] %s (by: %s)",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.deadline));
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.deadline);
    }
}
