public class Deadline extends Task {

    String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;

    }

    @Override
    public String toString() {
        return (isDone? "[D][X] " : "[D][ ] ") + description + (". Deadline: "  + this.deadline);
    }
}
