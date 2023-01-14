public class Deadline extends Task {
    private String from;
    private String to;

    public Deadline(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
 }
