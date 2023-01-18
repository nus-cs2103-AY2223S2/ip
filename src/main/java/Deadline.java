public class Deadline extends Task {

    private String endTime;

    public Deadline(String s, String endTime) {
        super(s);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        if (taskDone == false) {
            return "[D][ ] " + this.taskName + " (by: " + this.endTime + ")";
        }
        return "[D][X] " + this.taskName + " (by: " + this.endTime + ")";
    }

}
