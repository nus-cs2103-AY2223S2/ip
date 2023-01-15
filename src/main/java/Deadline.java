public class Deadline extends Task {
    private String dl;

    Deadline(String name, String deadline) {
        super(name);
        this.dl = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dl + ")";
    }
}
