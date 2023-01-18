public class Deadline extends Task {

    private String time;

    public Deadline(String s, String time) {
        super(s);
        this.time = time;
    }

    @Override
    public String toString() {
        if (taskDone == false) {
            return "[D][ ] " + this.taskName;
        }
        return "[D][X] " + this.taskName;
    }

}
