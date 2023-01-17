public class Deadline extends Task {
    private String date;

    public Deadline(String instruction, String date) {
        super(instruction.substring(9));
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}