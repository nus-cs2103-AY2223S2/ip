public class Deadline extends Task {
    private String byTime;

    public Deadline(String task, String type, String by) {
        super(task, type);
        byTime = by;
    }

    @Override
    public String toString() {
        return String.format("%s from: %s to: %s", super.toString(), byTime);
    }
}
