package task;
public class Deadline extends Task {

    private String endDate;
    public Deadline(String task, String endDate) {
        super(task);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endDate + ")";
    }
}
