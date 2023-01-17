public class Deadline extends Task{
    private String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
    @Override
    public String printTask() {
        return String.format("[D][%s] %s (by: %s)",
                (super.isDone() ? "X" : " "),
                super.getDescription(),
                this.deadline);
    }
}
