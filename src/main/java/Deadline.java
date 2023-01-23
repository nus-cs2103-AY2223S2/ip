public class Deadline extends Task {
    String dead;
    public Deadline(String details, String dead) {
        super(details);
        this.dead = dead;
    }

    @Override
    public String toString() {
        String task = super.toString();
        return "[D] " + task + " (by: " + dead + ")";
    }
}
