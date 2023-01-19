public class Deadline extends Quest {
    private String to;

    public Deadline(String description, String to) {
        super(description);
        this.to = to;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " by: " + to;
    }
}
