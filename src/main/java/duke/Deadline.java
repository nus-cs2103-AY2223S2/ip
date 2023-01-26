package duke;

public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

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
    
    @Override
    public String formatTask() {
        return String.format("deadline~-~-~%s~-~-~%s~-~-~%s", this.getDescription(), this.deadline, this.isDone() ? "X" : "O");
    }
}
