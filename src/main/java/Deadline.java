public class Deadline extends Task {
    private String date;

    public Deadline(String instruction, String date) {
        super(instruction);
        this.date = date.substring(3);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}
