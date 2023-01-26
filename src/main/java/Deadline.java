public class Deadline extends Task {

    private String deadline;
    Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String sendOutputToFile() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, deadline);
    }
}
