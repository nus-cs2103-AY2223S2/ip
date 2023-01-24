public class Deadline extends Task {
    protected String time;

    public Deadline(String cont, String time) {
        super(cont);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }
}
