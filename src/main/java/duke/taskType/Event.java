package duke.taskType;
public class Event extends Task {
    protected String time1;
    protected String time2;

    public Event(String cont, String time1, String time2){
        super(cont);
        this.time1 = time1;
        this.time2 = time2;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.time1 + " to: " + this.time2 + ")";
    }
}