public class Deadline extends Task {
    private String dl;

    Deadline(String name, String deadline) {
        super(name);
        this.dl = deadline;
    }

    Deadline(String name, String deadline, String status) {
        super(name, status);
        this.dl = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dl + ")";
    }

    @Override
    public String asTokens() {
        return "D," + super.asTokens() + ',' + this.dl;
    }
}
