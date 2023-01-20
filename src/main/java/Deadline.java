public class Deadline extends Task{

    protected String eventDetail;

    protected String time;
    public Deadline(String description) {
        super(description);
        String detail = description.split("/")[0].split(" ", 2)[1];
        this.eventDetail = detail;
        String time = description.split("/")[1].split(" ")[0] + ": " + description.split("/")[1].split(" ",2)[1];
        this.time = time;

    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + eventDetail + "(" + time + ")";
    }
}
