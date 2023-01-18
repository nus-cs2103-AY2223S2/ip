public class Deadline extends Task {
    private String endDate;

    protected Deadline(String name, String endDate) {
        super.name = name;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[D] " + this.TasktoString() + "(by: " + this.endDate + ")";
    }
}
