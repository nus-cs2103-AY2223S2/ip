public class Deadline extends Task {
    String deadlineDay;

    Deadline(String desc, boolean done, String deadlineDay) {
        super(desc, done);
        this.deadlineDay = deadlineDay;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + this.deadlineDay + ")";
    }
}
