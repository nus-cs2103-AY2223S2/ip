public class Deadline extends Task {
    private String deadlineDay;

    Deadline(String desc, boolean done, String deadlineDay) {
        super(desc, done);
        this.deadlineDay = deadlineDay;
    }

    String getDeadlineDay(){
        return this.deadlineDay;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + this.deadlineDay + ")";
    }
}
