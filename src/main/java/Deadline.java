public class Deadline extends Task {

    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
    @Override
    public String addToFile() {
        String str = String.format("D | %d | %s | %s ",
                isDone ? 1 : 0, this.description, this.deadline);
        return str + "\n";
    }
    @Override
    public String toString() {
        return String.format("[D][%s] %s(by: %s)",
                this.isDone ? "X" : " ", this.description,
                this.deadline);
    }
}
