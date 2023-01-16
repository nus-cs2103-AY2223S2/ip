public class Event extends Task {
    protected String start;
    protected String end;

    Event(String start, String end, String desc) {
        super(desc);
        this.start = start;
        this.end = end;
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + this);
        System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start + "to: " + end + ")";
    }
}
